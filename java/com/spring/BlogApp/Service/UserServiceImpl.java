package com.spring.BlogApp.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Dto.UserDto;
import com.spring.BlogApp.Exception.ResourceNotFoundException;
import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.Role;
import com.spring.BlogApp.Models.User;
import com.spring.BlogApp.Respository.CommentRepo;
import com.spring.BlogApp.Respository.PostRepo;
import com.spring.BlogApp.Respository.RoleRepo;
import com.spring.BlogApp.Respository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserDto> getAllUsers() {
		List<User>users=userRepo.findAll();
		List<UserDto>userDtos=users.stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(int id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found for userId "+id));
		UserDto userDto=modelMapper.map(user, UserDto.class);
//		List<Comment>comments=commentRepo.findByUser(user);
//		List<CommentDto>commentDtos=comments.stream().map((comment)->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//		List<Post>posts=postRepo.findByUser(user);
//		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		userDto.setPostDtos(postDtos);
//		System.out.println(user.getRoles());
//		userDto.setRoles(user.getRoles());
//		userDto.setCommentDtos(commentDtos);
		return userDto;
	}

	@Override
	public void deleteUser(int id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found for userId "+id));
		userRepo.delete(user);
	}

	@Override
	public UserDto createUser(UserDto userDto,Set<Integer> roleId) {
		User user=modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Set<Role> set=new HashSet<Role>();
		for(int id:roleId) {
			Optional<Role> role=roleRepo.findById(id);
			if(!role.isEmpty()) {
			set.add(role.get());
			}
		}
		user.setRoles(set);
		userDto.setRoles(set);
		userRepo.save(user);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id,Set<Integer>addRoleId,Set<Integer>deleteRoleId) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found for userId "+id));
		user.setId(id);
		user.setAbout(userDto.getAbout());
		user.setName(userDto.getName());
		//user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Set<Role>set=user.getRoles();
	   for(int roleId:addRoleId) {
		   Optional<Role> role=roleRepo.findById(roleId);
		   if(!role.isEmpty()) {
			   set.add(role.get());
		   }
	   }
	   for(int roleId:deleteRoleId) {
		  Optional<Role> role=roleRepo.findById(roleId);
		   if(!role.isEmpty()) {
			   set.remove(role.get());
		   }
	   }
	   
		user.setRoles(set);
		userRepo.save(user);
		return modelMapper.map(user,UserDto.class);
	}

}
