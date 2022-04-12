package com.springboot.blogApp.services.impel;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.exceptions.ResourceNotFoundException;
import com.springboot.blogApp.payloads.CategoryDto;
import com.springboot.blogApp.repositories.CategoryRepo;
import com.springboot.blogApp.services.CategoryService;

@Service
public class CategoryServiceImpel implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cate = this.modelmapper.map(categoryDto, Category.class);
		Category saveCategory = this.categoryRepo.save(cate);
		
		return this.modelmapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Catergory", "Catrgory Id", categoryId));		
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> allCategories = this.categoryRepo.findAll().stream().map(cat -> this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return allCategories;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(cat);
		return this.modelmapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
		return this.modelmapper.map(cat, CategoryDto.class);
	}



}
