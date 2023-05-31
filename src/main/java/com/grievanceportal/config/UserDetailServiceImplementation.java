package com.grievanceportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.grievanceportal.dao.UserRepository;
import com.grievanceportal.entity.User;

public class UserDetailServiceImplementation implements UserDetailsService {
    @Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.getUserbyUserName(username);
	if(user==null) {
		throw new UsernameNotFoundException("Could not found user");
	}
	CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails ;
	}

}
