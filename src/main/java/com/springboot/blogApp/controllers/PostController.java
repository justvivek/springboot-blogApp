package com.springboot.blogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogApp.payloads.ApiResponse;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	//Create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto postCreated = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(postCreated, HttpStatus.CREATED);
	}
	
	
	//Update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	
	//Get All posts
	@GetMapping("/posts/")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> allPosts = this.postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}
	
	
	//Get a post with postId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		PostDto post = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	
	//Get All posts of a User 
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);	
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}
	
	//Delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Delete is successful having Id:- " + postId, true), HttpStatus.OK);
	}
	
	//Search key
	@GetMapping("/posts/{keyword}/")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
}
