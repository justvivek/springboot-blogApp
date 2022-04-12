package com.springboot.blogApp.services;

import java.util.List;

import com.springboot.blogApp.payloads.CategoryDto;

public interface CategoryService {
	
	//create 
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//get 
	CategoryDto getCategory(Integer categoryId);
	
	//get All
	List<CategoryDto> getAllCategory();
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	CategoryDto deleteCategory(Integer categoryId);
	


}
