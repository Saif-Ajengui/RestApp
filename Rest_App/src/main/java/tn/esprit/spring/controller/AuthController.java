package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.User;
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
			user.setPwd(encoder.encode(user.getPwd()));
			user.setApproved(false);
			userService.Register(user);

			SimpleMailMessage AppEmail = new SimpleMailMessage();
			AppEmail.setTo(user.getEmail());
			AppEmail.setSubject("Registration");
			AppEmail.setText(
					"Dear " + user.getLname() + ":\n" + "Félicitation !" 
							+ ":\n" 
							+ "Your role is " + user.getType()
							+ ".\n"
							+"Rest App :)") ;

			sendEmail.sendEmail(AppEmail);
			
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
