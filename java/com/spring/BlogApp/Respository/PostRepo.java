package com.spring.BlogApp.Respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.BlogApp.Models.Category;
import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

	//List<Post> findByUser(User user);
	Page<Post> findByCategory(Pageable pageable, Category category);
	Page<Post> findByUser(Pageable pageable,User user);
	Page<Post> findByCategoryAndUser(Pageable pageable,Category category,User user);
}
