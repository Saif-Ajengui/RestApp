package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.List;

import tn.esprit.spring.entity.Task;
import tn.esprit.spring.entity.User;

public interface UserService {
	
	List<Integer> getUserByDept(String deptName);
	
	Iterable<User> getUsers();
	

	void assignTaskToUser(int idTask, int idUser);
	
	void unassignTaskToUser(int idTask, int idUser);

	List<Task> retrieveTasksByUser(int idUser);



	

	

}
