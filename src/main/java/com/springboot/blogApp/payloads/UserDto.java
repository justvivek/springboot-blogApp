package com.springboot.blogApp.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min =4, message= "Username name should be of minimum 4 characters")
	private String name;
	
	@Email(message = "Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 32)
	private String password;
	
	@NotEmpty
	private String about;

}
