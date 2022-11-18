package com.git.gabriele.service;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.git.gabriele.dto.CreateUserRequest;
import com.git.gabriele.exception.NotFoundUserException;
import com.git.gabriele.model.User;
import com.git.gabriele.repositoriy.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    public static final String MESSAGE_USER_NOT_FOUND = "User Not Found";

    @Inject
    UserRepository repository;

    @Inject
    Validator validator;

    @Transactional
    public User saveUser(CreateUserRequest userRequest){

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);
        return user;
    }
    public List<User> findAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return query.list();
    }
    @Transactional
    public void delete(Long id){
        User user = repository.findById(id);
        if(user == null){
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
        repository.delete(user);
    }
    @Transactional
    public void update(Long id, CreateUserRequest userData){
        User user = repository.findById(id);
        if (user == null) {
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
            user.setName(userData.getName());
            user.setAge(userData.getAge());
    }
}

