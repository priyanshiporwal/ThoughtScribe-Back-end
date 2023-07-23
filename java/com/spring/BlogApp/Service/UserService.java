package com.spring.BlogApp.Service;

import java.util.List;
import java.util.Set;

import com.spring.BlogApp.Dto.UserDto;

public interface UserService {

	List<UserDto> getAllUsers();
	UserDto getUserById(int id);
	void deleteUser(int id);
	UserDto createUser(UserDto userDto,Set<Integer> roleId);
	UserDto updateUser(UserDto userDto,int id,Set<Integer>addRoleId,Set<Integer>deleteRoleId);
}
