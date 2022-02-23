package tn.esprit.spring.controller;

import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/User")
public class UserControl {
	
	@Autowired
	private UserService userService;

}
