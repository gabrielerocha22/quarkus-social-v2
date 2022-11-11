package com.git.gabriele.repositoriy;

import javax.enterprise.context.ApplicationScoped;

import com.git.gabriele.model.Post;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post>{
	

}
