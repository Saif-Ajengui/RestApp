package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Task;
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
	
	@GetMapping("/details")
	public User getUserDetails(@AuthenticationPrincipal UserDetails userAuth ) {
		String e=userAuth.getUsername();
		User u =userRepository.findUserByEmail(e);
		return u;
	}

	// http://localhost:8082/examen/User/getUserByDept/deptName 
	@GetMapping("/getUserByDept/{deptName}")
	public List<Integer> getUserByDept(@PathVariable("deptName") String deptName) {
		return userService.getUserByDept(deptName); 
 
	} 

	// http://localhost:8082/examen/User/getUsers 
	@GetMapping("/getUsers")
	public Iterable<User> getUsers() {
		return userService.getUsers();

	}

	// http://localhost:8082/examen/User/assignTaskToUser/idTask/idUser
	@PutMapping("/assignTaskToUser/{idTask}/{idUser}")
	public String assignTaskToUser(@PathVariable("idTask") int idTask, @PathVariable("idUser") int idUser) {
		userService.assignTaskToUser(idTask, idUser);
		return "The chosen task is successfulle assigned to the user:" + idUser;

	}

	// http://localhost:8082/examen/User/assignTaskToDept/{idTask}/{deptName}
	@PutMapping("/assignTaskToDept/{idTask}/{deptName}")
	public String assignTaskToDept(@PathVariable("idTask") int idTask, @PathVariable("deptName") String deptName) {
		List<Integer> deptUsers = null;
		deptUsers = userService.getUserByDept(deptName);
		int nbUs = deptUsers.size();
		for (int i = 0; i < nbUs; i++) {
			int idUs = deptUsers.get(i);
			userService.assignTaskToUser(idTask, idUs);
		}

		return "The chosen task is successfully assigned to the following " + deptName + " members: " + deptUsers;
	}

	// http://localhost:8082/examen/User/unassignTaskToDept/{idTask}/{deptName}
	@PutMapping("/unassignTaskToDept/{idTask}/{deptName}")
	public String unassignTaskToDept(@PathVariable("idTask") int idTask, @PathVariable("deptName") String deptName) {
		List<Integer> deptUsers = null;
		deptUsers = userService.getUserByDept(deptName);
		int nbUs = deptUsers.size();
		for (int i = 0; i < nbUs; i++) {
			int idUs = deptUsers.get(i);
			userService.unassignTaskToUser(idTask, idUs);
		}

		return "The choosen task is successfully unassigned from the following " + deptName + " members: " + deptUsers;
	}

	// http://localhost:8082/User/unassignTaskToUser/idTask/idUser
	@PutMapping(value = "/unassignTaskToUser/{idTask}/{idUser}")
	public void unassignTaskToUser(@PathVariable("idTask") int idTask, @PathVariable("idUser") int idUser) {
		userService.unassignTaskToUser(idTask, idUser);
	}

	// http://localhost:8082/examen/User/retrieveTasksByUser/idUser
	@GetMapping("/retrieveTasksByUser/{idUser}")
	public List<Task> retrieveTasksByUser(@PathVariable("idUser") int idUser) {
		return userService.retrieveTasksByUser(idUser);

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
	
	
}
