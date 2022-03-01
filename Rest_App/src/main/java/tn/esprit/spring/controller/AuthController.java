package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.response.AuthDetailsUser;
import tn.esprit.spring.response.JwtUtils;
import tn.esprit.spring.response.MessageResponse;
import tn.esprit.spring.service.ISendEmail;
import tn.esprit.spring.service.IUserService;
import tn.esprit.spring.service.UserService;



@RestController
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
	JwtUtils jwtUtils;
	
	
	@PostMapping("/login")
	public String authenticateUser(@Valid @RequestBody User login) throws Exception {
		String resultat = userService.Authentication(login.getEmail(),
				login.getPwd());
		/*if (resultat.length() ==0) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPwd()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			AuthDetailsUser userDetails = (AuthDetailsUser) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return jwt;
		}*/
		
		return resultat;
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
							+ "http://localhost:8082/examen/Authentification/ApprovedProfile/"+user.getId()
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
	
	
	@PutMapping("/UpdateUser/{id}")
	@ResponseBody
	public User updateUser(@RequestBody User user, @PathVariable("id") int id) throws Exception {
		User u = userRepository.findUserById(id);
		if (!encoder.encode(user.getPwd()).equals(u.getPwd())) {
			user.setPwd(encoder.encode(user.getPwd()));
		}
		return userService.UpdateProfile(id, user);
	}
	
	@GetMapping("/ShowProfile/{id}")
	public User getUserById(@PathVariable("id") int id) throws Exception {
		return userService.ShowProfile(id);
	}
	
	@PostMapping("/forgot/{email}")
	public String processForgotPasswordForm(@PathVariable("email") String email,
			HttpServletRequest request) throws Exception {
		User user = userRepository.findUserByEmail(email);

		if (user == null) {
			return "user not found";
		} else {
			//token 
			user.setResettoken(UUID.randomUUID().toString());

			userService.updatePassword(user);

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Reset Password");
			passwordResetEmail.setText("Dear " + user.getLname()
					+ "\n "
					+"To reset your password, click the link below: \n "
					+ "https://localhost:8082/examen/Authentification/reset/"
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
