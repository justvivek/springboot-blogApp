package com.springboot.blogApp.services.impel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exceptions.ResourceNotFoundException;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.repositories.CategoryRepo;
import com.springboot.blogApp.repositories.PostRepo;
import com.springboot.blogApp.repositories.UserRepo;
import com.springboot.blogApp.services.PostService;

@Service
public class PostServiceImpel implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User_Id", userId));
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category_Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageUrl("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		
		Post savedPost =postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	//Update Post content/ Title having id postId 
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageUrl(postDto.getImageUrl());
		post.setAddedDate(new Date());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	//Get all posts
	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> allPostsDto = allPosts.stream()
											.map(post -> this.modelMapper.map(post, PostDto.class))
											.collect(Collectors.toList());
		return allPostsDto;
	}

	
	//Get post by postId
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}
	
	
	//Get all posts in particular categoryId
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> postsByCategory = this.postRepo.findByCategory(cat);
		List<PostDto> postsByCategoryDto =  postsByCategory.stream()
															.map(post -> modelMapper.map(post, PostDto.class))
															.collect(Collectors.toList());
		return postsByCategoryDto;
	}

	//get all posts by a user
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> postByUser = postRepo.findByUser(user);
		List<PostDto> postByUserDto = postByUser.stream()
												.map(post -> modelMapper.map(post, PostDto.class))
												.collect(Collectors.toList());
		return postByUserDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findAll();
		
		List<Post> postWithKeyword = posts.stream()
										  .filter(post -> (post.getContent().contains(keyword) || post.getTitle().contains(keyword)))
										  .collect(Collectors.toList());
		if(postWithKeyword.size() != 0) {
			return postWithKeyword.stream()
								   .map(post -> this.modelMapper.map(post, PostDto.class))
								   .collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

}
