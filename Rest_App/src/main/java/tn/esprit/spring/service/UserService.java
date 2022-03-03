package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.User;

public interface UserService {

	void assignTaskToUser(int idTask, int idUser);
	
	void unassignTaskToUser(int idTask, int idUser);

	List<Integer> getUserByDept(String deptName);

	

}
