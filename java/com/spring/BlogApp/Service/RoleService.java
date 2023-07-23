package com.spring.BlogApp.Service;

import java.util.List;

import com.spring.BlogApp.Models.Role;

public interface RoleService {

	List<Role> getAllRoles();
	Role createRole(Role role);
}
