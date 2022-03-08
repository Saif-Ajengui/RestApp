package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/User")
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository userRepository;
	
	
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
	
	
}
