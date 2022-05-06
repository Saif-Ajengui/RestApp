package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.config.FileUpload;
import tn.esprit.spring.config.JwtTokenUtil;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.jwt.JwtRequest;
import tn.esprit.spring.jwt.JwtResponse;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.response.MessageResponse;
import tn.esprit.spring.service.ISendEmail;
import tn.esprit.spring.service.IUserService;
import tn.esprit.spring.service.UserService;



@RestController
@CrossOrigin
@RequestMapping("/Authentification")
public class AuthController {

	@Autowired
	IUserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private ISendEmail sendEmail;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	

	
	@PostMapping( "/login")
	
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		String resultat = userService.Authentication(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		if (resultat=="User unfound") {
			return ResponseEntity.ok("User unfound");
		}else if (resultat=="unverified account") {
			return ResponseEntity.ok("unverified account");
		}else if (resultat=="password wrong") {
			return ResponseEntity.ok("password wrong");
		}else{
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
		}
		
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	
	
	@PostMapping(value="/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
		@ResponseBody
		public ResponseEntity<?> createUser(@RequestParam(value="fname") String fname,
				@RequestParam(value="lname") String lname,
				@RequestParam(value="email") String email,
				@RequestParam(value="pwd") String pwd,
				@RequestParam(value="birthdate") String birthdate,
				@RequestParam(value="type") Role type,
				@RequestParam(value="file") MultipartFile file) throws Exception {
			
			if (fname.equals("")) { 
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add first name!"));
			}
			if (lname.equals("")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add last name!"));
			}
			if (birthdate.equals("")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
			}
			if (email.equals("")|| !UserService.validate(email)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add email!"));
			}
			if (pwd.equals("") ) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add password!"));
			}
			if (userService.isFindEmail(email)==true ) {
				return ResponseEntity.badRequest().body(new MessageResponse("account is already exist"));
			}
			User user=new User();
			user.setPwd(encoder.encode(pwd));
			user.setApproved(false);
			user.setLname(lname);
			user.setFname(fname);
			user.setEmail(email);
			user.setType(type);
			user.setBirthdate(birthdate);
			String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
			String uploadDir = "uploads/";
			try {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
						.path(fileNameRepo).toUriString();
				
			//	user.setPic(fileDownloadUri.getBytes());
				user.setPic(file.getBytes());
				FileUpload.saveFile(uploadDir, fileNameRepo, file);
				System.out.println("file url =====>" + file);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			userService.Register(user);

			SimpleMailMessage AppEmail = new SimpleMailMessage();
			AppEmail.setTo(user.getEmail());
			AppEmail.setSubject("Registration");
			AppEmail.setText(
					"Dear " + user.getLname() + "\n"+"\n" + "Félicitation !" 
							+ ":\n" 
							+ "Your Role is " + user.getType()
							+ ".\n"+"Approved your account "
							+ "http://localhost:8082/Authentification/ApprovedProfile/"+user.getId()
							+"\n"+"\n"
							+"Cordialement / Rest App.") ;

			sendEmail.sendEmail(AppEmail);
			
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@GetMapping("/ApprovedProfile/{id}")
	public String ApprovedProfile(@PathVariable("id") int id){
		userService.ApprovedProfile(id);
		return "Account is Approved";
	}
	
	
	
	@PostMapping("/forgot/{email}")
	public  ResponseEntity<?> processForgotPasswordForm(@PathVariable("email") String email,
			HttpServletRequest request) throws Exception {
		String resetT="";
		User user = userRepository.findUserByEmail(email);

		if (user == null) {
			return  ResponseEntity.ok("user not found"); 
		} else {
			//resettoken 
			 resetT +=user.setResettoken(UUID.randomUUID().toString());

			userService.updatePassword(user);

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Reset Password");
			passwordResetEmail.setText("Dear " + user.getLname()
					+ "\n "
					+"To reset your password, click the link below: \n "
					+ "https://http://localhost:4200/resetpassword"
					+ "\n "
					+"\n Your token is: "
					+ user.getResettoken());
 
			sendEmail.sendEmail(passwordResetEmail);
		}
		return  ResponseEntity.ok(new JwtResponse(resetT));

	}
	@PostMapping("/reset/{token}/{newpwd}") 
	public String setNewPassword(@PathVariable("token") String token,@PathVariable("newpwd") String newpassword ) throws Exception {
		User user = userRepository.findUserByToken(token);
		if (user != null) {
			user.setPwd(encoder.encode(newpassword));
			user.setResettoken(null);
			userService.updatePassword(user);
			return "password is reseted";

		} else {
			return "operation is rejected";
		}
	}
	
	
	
}
