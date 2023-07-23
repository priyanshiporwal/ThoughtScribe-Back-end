package com.spring.BlogApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BlogApp.Dto.CategoryDto;
import com.spring.BlogApp.Service.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@GetMapping("/all")
	ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categoryDtos = categoryServiceImpl.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<CategoryDto> getUser(@PathVariable int id) {
		CategoryDto categoryDto = categoryServiceImpl.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUser(@PathVariable int id) {
		categoryServiceImpl.deleteCategory(id);
		return new ResponseEntity<String>("Category Deleted Successfully", HttpStatus.OK);
	}

	@PostMapping("/create")
	ResponseEntity<CategoryDto> createUser(@RequestBody @Valid CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryServiceImpl.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	ResponseEntity<CategoryDto> updateUser(@RequestBody CategoryDto categoryDto, @RequestParam int id) {
		CategoryDto categoryDto2 = categoryServiceImpl.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.OK);
	}
}
