package com.git.gabriele.dto;

import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public class CreateUserRequest {
	
	@NotBlank (message = "Name is Required")
	private String name;
	
	@NotNull 
	private Integer age;
	
<<<<<<< HEAD
	
=======
>>>>>>> 6f609a0 (add exceptions)
	public CreateUserRequest(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
<<<<<<< HEAD
	
	

=======
>>>>>>> 6f609a0 (add exceptions)
}
