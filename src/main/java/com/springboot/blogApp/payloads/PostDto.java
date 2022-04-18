package com.springboot.blogApp.payloads;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {

	private String title;

	private String content;
	
	private String imageUrl;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
