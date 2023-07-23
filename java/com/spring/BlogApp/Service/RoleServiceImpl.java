package com.spring.BlogApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.BlogApp.Models.Role;
import com.spring.BlogApp.Respository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Role createRole(Role role) {
		roleRepo.save(role);
		return role;
	}

}
