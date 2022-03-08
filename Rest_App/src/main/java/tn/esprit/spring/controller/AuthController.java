package tn.esprit.spring.controller;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.config.JwtTokenUtil;
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
	
	
	
	
	@PostMapping("/signup")
		@ResponseBody
		public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
			if (user == null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add values!"));
			}
			if (user.getFname().equals("")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add first name!"));
			}
			if (user.getLname().equals("")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add last name!"));
			}
			if (!(user.getBirthdate() instanceof Date)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
			}
			if (user.getEmail().equals("")|| !UserService.validate(user.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add email!"));
			}
			if (user.getPwd().equals("") ) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: please add password!"));
			}
			if (userService.isFindEmail(user.getEmail())==true ) {
				return ResponseEntity.badRequest().body(new MessageResponse("account is already exist"));
			}
			user.setPwd(encoder.encode(user.getPwd()));
			user.setApproved(false);
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
	public String processForgotPasswordForm(@PathVariable("email") String email,
			HttpServletRequest request) throws Exception {
		User user = userRepository.findUserByEmail(email);

		if (user == null) {
			return "user not found"; 
		} else {
			//resettoken 
			user.setResettoken(UUID.randomUUID().toString());

			userService.updatePassword(user);

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Reset Password");
			passwordResetEmail.setText("Dear " + user.getLname()
					+ "\n "
					+"To reset your password, click the link below: \n "
					+ "https://localhost:8082/Authentification/reset/"
					+ "\n "
					+"\n Your token is: "
					+ user.getResettoken());

			sendEmail.sendEmail(passwordResetEmail);
		}
		return "Mail sent";

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
