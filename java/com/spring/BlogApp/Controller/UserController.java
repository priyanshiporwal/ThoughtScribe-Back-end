package com.spring.BlogApp.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BlogApp.Dto.UserDto;
import com.spring.BlogApp.Service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/all")
	ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> userDtos = userServiceImpl.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserDto> getUser(@PathVariable int id) {
		UserDto userDto = userServiceImpl.getUserById(id);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUser(@PathVariable int id) {
		userServiceImpl.deleteUser(id);
		return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
	}

	@PostMapping("/create")
	ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto,
			@RequestParam(defaultValue = "2") Set<Integer> roleId) {
		UserDto userDto2 = userServiceImpl.createUser(userDto, roleId);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, int id,
			@RequestParam(required = false, defaultValue = "0") Set<Integer> addRoleId,
			@RequestParam(required = false, defaultValue = "0") Set<Integer> deleteRoleId) {
		UserDto userDto2 = userServiceImpl.updateUser(userDto, id, addRoleId, deleteRoleId);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.OK);
	}
}
