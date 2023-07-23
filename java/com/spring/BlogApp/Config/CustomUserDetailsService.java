package com.spring.BlogApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.BlogApp.Models.User;
import com.spring.BlogApp.Respository.UserRepo;

public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		UserDetails userDetails = new CustomUserDetails(user);
		return userDetails;

	}

}
