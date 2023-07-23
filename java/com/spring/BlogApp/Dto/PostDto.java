package com.spring.BlogApp.Dto;

import java.util.Date;
import java.util.List;

public class PostDto {

	private int id;
	private String title;
	private String description;
	private String image;
	private UserDto user;
	private CategoryDto category;
	private Date addedDate;
	private List<CommentDto>commentDtos;
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public PostDto(int id, String title, String description, String image, UserDto user, CategoryDto category,
			Date addedDate, List<CommentDto> commentDtos) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.user = user;
		this.category = category;
		this.addedDate = addedDate;
		this.commentDtos = commentDtos;
	}



	public List<CommentDto> getCommentDtos() {
		return commentDtos;
	}



	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
}
