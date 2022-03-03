package tn.esprit.spring.controller;

import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.*;
import tn.esprit.spring.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserControl {

	@Autowired
	private UserService userService;

	// http://localhost:8082/examen/User/getUserByDept/deptName
	@GetMapping("/getUserByDept/{deptName}")
	public List<Integer> getUserByDept(@PathVariable("deptName") String deptName) {
		return userService.getUserByDept(deptName);

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

		return "The choosen task is successfully assigned to the following " + deptName + " members: " + deptUsers;
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

	// http://localhost:8082/examen/User/unassignTaskToUser/idTask/idUser
	@PutMapping(value = "/unassignTaskToUser/{idTask}/{idUser}")
	public void unassignTaskToUser(@PathVariable("idTask") int idTask, @PathVariable("idUser") int idUser) {
		userService.unassignTaskToUser(idTask, idUser);
	}

}
