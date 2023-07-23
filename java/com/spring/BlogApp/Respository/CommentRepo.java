package com.spring.BlogApp.Respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.BlogApp.Models.Comment;
import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.User;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByUser(User user);
	//List<Comment> findByPost(Post post);
	Page<Comment> findByPost(Pageable pageable,Post post);
}
