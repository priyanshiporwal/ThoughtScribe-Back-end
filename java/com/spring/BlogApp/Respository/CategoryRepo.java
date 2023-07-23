package com.spring.BlogApp.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.BlogApp.Models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
