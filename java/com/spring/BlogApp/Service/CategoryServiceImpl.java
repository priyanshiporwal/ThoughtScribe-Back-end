package com.spring.BlogApp.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.BlogApp.Dto.CategoryDto;
import com.spring.BlogApp.Exception.ResourceNotFoundException;
import com.spring.BlogApp.Models.Category;
import com.spring.BlogApp.Respository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories=categoryRepo.findAll();
		List<CategoryDto>categoryDtos=categories.stream().map((category->modelMapper.map(category,CategoryDto.class))).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found for id "+id));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found for id "+id));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found for id "+id));
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		categoryRepo.save(category);
		return categoryDto;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto,Category.class);
		categoryRepo.save(category);
		return categoryDto;
	}

}
