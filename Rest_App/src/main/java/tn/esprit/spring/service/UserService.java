package tn.esprit.spring.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	PasswordEncoder encoder;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}

	@Override
	public boolean isFindEmail(String email) {
		List<User> user=  userRepository.findUserByEmailExist(email);
        if(user.size()>0){
        	return true;
        }else {
        	return false;
        }
	}
	
	
	
	@Override
	public void Register(User user) {
		userRepository.save(user);
		
	}

	@Override
	public String Authentication(String email, String pwd) {
		User user =  userRepository.findUserByEmail(email);
    	if (user==null){
    		return "User unfound";
    	
    	}else if (user.isApproved()==false) {
    		return "unverified account";
    	}else if (encoder.matches(pwd, user.getPwd())) {
            
    		return "Welcom";
    	}
		return "password wrong";
	}

	@Override
	public User ShowProfile(int id) {
		
		return userRepository.findUserById(id);
	}

	@Override
	public User UpdateProfile(int id, User user) {
		User u = userRepository.findUserById(id);
		u.setFname(user.getFname());
		u.setLname(user.getLname());
		u.setPic(user.getPic());
		u.setBirthdate(user.getBirthdate());
		u.setEmail(user.getEmail());
		u.setType(user.getType());
		return userRepository.save(u);
	}

	@Override
	public void ApprovedProfile(int id) {
		userRepository.updateApp(id);
	}
	@Override
	public User updatePassword(User u) throws Exception {
		User user = userRepository.findUserById(u.getId());
		if (user!=null) {
			return userRepository.save(u);
		} else {
			throw new Exception("No the user record exist");
		}
	}

	

}
