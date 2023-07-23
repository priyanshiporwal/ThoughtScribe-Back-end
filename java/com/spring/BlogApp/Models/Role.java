package com.spring.BlogApp.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	private int id;
	private String name;
	
	@ManyToMany
	List<User>users=new ArrayList<>();

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Role(int id, String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
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

	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}

	@JsonProperty
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
