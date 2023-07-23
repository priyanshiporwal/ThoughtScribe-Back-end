package com.spring.BlogApp.Models;

import java.util.Date;

import org.hibernate.engine.jdbc.mutation.internal.PreparedStatementGroupStandard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;
	private Date date;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(int id, String content, User user, Date date,Post post) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.date = date;
		this.post=post;
	}
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
