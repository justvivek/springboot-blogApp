package com.springboot.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogApp.entities.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
