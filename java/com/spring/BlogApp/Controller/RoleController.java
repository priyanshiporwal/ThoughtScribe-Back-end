package com.spring.BlogApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BlogApp.Models.Role;
import com.spring.BlogApp.Service.RoleServiceImpl;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roles = roleServiceImpl.getAllRoles();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<Role> create(@RequestBody Role role) {
		Role role2 = roleServiceImpl.createRole(role);
		return new ResponseEntity<Role>(role2, HttpStatus.CREATED);
	}

}
