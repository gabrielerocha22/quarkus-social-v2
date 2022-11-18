package com.git.gabriele.repositoriy;

import javax.enterprise.context.ApplicationScoped;
<<<<<<< HEAD

import com.git.gabriele.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;


=======
import com.git.gabriele.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

>>>>>>> 6f609a0 (add exceptions)
@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
	
}
