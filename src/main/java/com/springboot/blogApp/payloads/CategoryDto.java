package com.springboot.blogApp.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	@NotEmpty
	private String categoryDescription;
}
