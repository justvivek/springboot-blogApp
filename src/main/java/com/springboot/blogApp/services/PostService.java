package com.springboot.blogApp.services;

import java.util.List;

import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.payloads.PostDto;


public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// getAll
	List<PostDto> getAllPost();

	// getPost by Id
	PostDto getPostById(Integer postId);

	// Get all posts of a Category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// Get All posts of a User
	List<PostDto> getPostsByUser(Integer userId);

	// Search
	List<PostDto> searchPosts(String keyword);

	// delete
	void deletePost(Integer postId);

}
