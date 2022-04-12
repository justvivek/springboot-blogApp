package com.springboot.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogApp.entities.User;


public interface UserRepo extends JpaRepository<User, Integer>{

}
