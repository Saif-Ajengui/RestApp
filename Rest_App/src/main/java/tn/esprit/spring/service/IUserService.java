package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface IUserService {

	void Register (User user);
	String Authentication(String email , String pwd);
	User ShowProfile(int id);
	void UpdateProfile(int id , User user);
	boolean ApprovedProfile(int id);
	
}
