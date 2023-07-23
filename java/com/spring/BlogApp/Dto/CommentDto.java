package com.spring.BlogApp.Dto;

import java.util.Date;

public class CommentDto {

	private int id;
	private String content;
	private UserDto user;
	private Date dateTime;
	private PostDto post;
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommentDto(int id, String content, UserDto user, Date dateTime,PostDto post) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.dateTime = dateTime;
		this.post=post;
	}
	

	public PostDto getPostDto() {
		return post;
	}

	public void setPostDto(PostDto post) {
		this.post = post;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public Date getDate() {
		return dateTime;
	}
	public void setDate(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
