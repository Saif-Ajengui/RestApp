package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface IUserService {

	void Register (User user);
	String Authentication(String email , String pwd);
	User ShowProfile(int id);
	User UpdateProfile(int id , User user);
	void ApprovedProfile(int id);
	public User updatePassword(User u) throws Exception ;
	public boolean isFindEmail(String email) ;
	
}
