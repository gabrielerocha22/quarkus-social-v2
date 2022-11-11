package com.git.gabriele.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.git.gabriele.dto.CreatePostRequest;
import com.git.gabriele.dto.PostResponse;
import com.git.gabriele.model.Post;
import com.git.gabriele.model.User;
import com.git.gabriele.repositoriy.PostRepository;
import com.git.gabriele.repositoriy.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

@Path("/users/{userID}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

	private UserRepository userRepository;
	private PostRepository repository;

	@Inject
	public PostResource(UserRepository userRepository, PostRepository repository) {
		this.userRepository = userRepository;
		this.repository = repository;
	}

	@POST
	@Transactional
	public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request) {
		User user = userRepository.findById(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Post post = new Post();
		post.setText(request.getText());
		post.setUser(user);
		// post.setDataTime(LocalDateTime.now());
		repository.persist(post);
		return Response.status(Response.Status.CREATED).build();

	}


    @GET
    public Response listPosts(@PathParam("userId") Long userId, @HeaderParam("followerId") Long followerId){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(followerId == null){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("You forgot the header followerId").build();
        }

        User follower = userRepository.findById(followerId);
        if(follower == null){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("inexistent followerId").build();
        }

//        boolean follows = followerRepository.follows(follower, user);
//        if(!follows){
//            return Response.status(Response.Status.FORBIDDEN).
//                    entity("You can't see these posts").build();
//        }

        var query = repository.
                find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();

        var postResponseList = list.stream().map(post -> PostResponse.fromEntity(post)).
                collect(Collectors.toList());

        return Response.ok(postResponseList).build();
    }

}
