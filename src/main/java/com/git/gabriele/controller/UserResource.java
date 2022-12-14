package com.git.gabriele.controller;

import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.git.gabriele.dto.CreateUserRequest;
import com.git.gabriele.dto.ResponseError;
import com.git.gabriele.model.User;
import com.git.gabriele.repositoriy.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;


import com.git.gabriele.dto.CreateUserRequest;
import com.git.gabriele.model.User;
import com.git.gabriele.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private UserRepository repository;
	
	private Validator validator;

	@Inject
	public UserResource(UserRepository repository, Validator validator) {
		this.repository = repository;
		this.validator = validator;
	}

	@POST
	@Transactional
	public Response createUser(CreateUserRequest userRequest) {
		
		Set<ConstraintViolation<CreateUserRequest>>  violations = validator.validate(userRequest);
		if(!violations.isEmpty()) {
			return  ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
			
		}
		
		User user = new User();
		user.setAge(userRequest.getAge());
		user.setName(userRequest.getName());

		repository.persist(user);
		return Response.status(Response.Status.CREATED.getStatusCode()).entity(user).build();
	}

	@GET
	public Response listAllUsers() {
		PanacheQuery<User> query = repository.findAll();
		return Response.ok(query.list()).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response deleteUser(@PathParam("id") Long id) {
		User user = repository.findById(id);
		if (user != null) {
			repository.delete(user);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {

		User user = repository.findById(id);

		if (user != null) {
			user.setName(userData.getName());
			user.setAge(userData.getAge());
			return Response.ok().build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
