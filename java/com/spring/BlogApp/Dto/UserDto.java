package com.spring.BlogApp.Dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.BlogApp.Models.Role;



public class UserDto {
	private int id;
	private String name;
	private String username;
	private String password;
	private String about;
	private Set<Role>roles;


//	private List<CommentDto>commentDtos;
//	private List<PostDto>postDtos;

	

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public UserDto(int id, String name, String username, String password, String about, Set<Role> roles
			) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.about = about;
		this.roles = roles;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
