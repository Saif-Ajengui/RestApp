package tn.esprit.spring.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IUserService;

@Slf4j
@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}
	
	@Override
	public void Register(User user) {
		userRepository.save(user);
		
	}

	@Override
	public String Authentication(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User ShowProfile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateProfile(int id, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ApprovedProfile(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
