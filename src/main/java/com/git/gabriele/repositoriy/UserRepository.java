package com.git.gabriele.repositoriy;

import javax.enterprise.context.ApplicationScoped;

import com.git.gabriele.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import com.git.gabriele.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
	
}
