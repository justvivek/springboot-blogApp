package com.springboot.blogApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name ="user_name", nullable = false, length = 100)
	private String name;
	
	@Column(name ="user_email", nullable = false, length = 50)
	private String email;
	
	@Column(name ="user_pass", nullable = false, length = 32)
	private String password;
	
	@Column(name ="about", length = 1000)
	private String about;
	
}
