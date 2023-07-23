package com.spring.BlogApp.Config;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.Role;
import com.spring.BlogApp.Models.User;

@Component
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	public CustomUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		Set<Role> set = user.getRoles();
		List<SimpleGrantedAuthority> roles = set.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		return roles;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	public String getName() {
		return user.getName();
	}
	
	public int getId() {
		return user.getId();
	}
	
	public String getAbout() {
		return user.getAbout();
	}
	
	public Set<Role> getRoles(){
		System.out.println(user.getRoles());
		return user.getRoles();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
