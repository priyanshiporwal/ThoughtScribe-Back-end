package com.spring.BlogApp.Service;

import java.util.List;

import com.spring.BlogApp.Dto.CategoryDto;

public interface CategoryService {

	List<CategoryDto> getAllCategories();
	CategoryDto getCategoryById(int id);
	void deleteCategory(int id);
	CategoryDto updateCategory(CategoryDto categoryDto,int id);
	CategoryDto createCategory(CategoryDto categoryDto);
}
