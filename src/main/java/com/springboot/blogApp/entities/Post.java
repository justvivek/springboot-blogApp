package com.springboot.blogApp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="posts")
@Data
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "post_content", length =10000)
	private String content;
	
	@Column(name ="post_ImageUrl")
	private String ImageUrl;
	
	@Column(name ="post_addedDate")
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name ="category_id", nullable = false)
	private Category category;
	
	@ManyToOne
	private User user;
	
	
}
