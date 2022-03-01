package tn.esprit.spring.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tn.esprit.spring.entity.User;



public class AuthDetailsUser implements UserDetails  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public AuthDetailsUser(User user,
			Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

}
