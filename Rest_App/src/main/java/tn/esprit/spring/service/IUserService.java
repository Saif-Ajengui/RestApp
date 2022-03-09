package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Task;
import tn.esprit.spring.entity.User;

public interface IUserService {

	void Register (User user);
	String Authentication(String email , String pwd);
	User ShowProfile(int id);
	User UpdateProfile(int id , User user);
	void ApprovedProfile(int id);
	public User updatePassword(User u) throws Exception ;
	public boolean isFindEmail(String email) ;
	
	//fatma
	List<Integer> getUserByDept(String deptName);
	
	Iterable<User> getUsers();
	

	void assignTaskToUser(int idTask, int idUser);
	
	void unassignTaskToUser(int idTask, int idUser);

	List<Task> retrieveTasksByUser(int idUser);
	
}
