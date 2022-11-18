package com.git.gabriele.controller;

<<<<<<< HEAD
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
=======
import javax.inject.Inject;
>>>>>>> 6f609a0 (add exceptions)
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.git.gabriele.dto.FollowerRequest;
<<<<<<< HEAD
import com.git.gabriele.dto.FollowerResponse;
import com.git.gabriele.dto.FollowersPerUserResponse;
import com.git.gabriele.model.Follower;
import com.git.gabriele.repositoriy.FollowerRepository;
import com.git.gabriele.repositoriy.UserRepository;

@Path("/user/{userId}/followers")
=======
import com.git.gabriele.dto.FollowersPerUserResponse;
import com.git.gabriele.service.FollowerService;

@Path("/users/{userId}/followers")
>>>>>>> 6f609a0 (add exceptions)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

<<<<<<< HEAD
	private FollowerRepository repository;
	private UserRepository userRepository;

	@Inject
	public FollowerResource(FollowerRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@PUT
	@Transactional
	public Response followUser(@PathParam("userId") Long userId, FollowerRequest request) {

		if (userId.equals(request.getFollowerId())) {
			return Response.status(Response.Status.CONFLICT).entity("You can't follow yourself").build();
		}
		var user = userRepository.findById(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		var follower = userRepository.findById(request.getFollowerId());

		boolean follows = repository.follows(follower, user);

		if (!follows) {
			var entity = new Follower(userId, follower, follower);
			entity.setUser(user);
			entity.setFollower(follower);
			repository.persist(entity);
		}

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@GET
	public Response listFollowers(@PathParam("userId") Long userId) {

		var user = userRepository.findById(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		var list = repository.findByUser(userId);
		FollowersPerUserResponse responseObject = new FollowersPerUserResponse();
		responseObject.setFollowersCount(list.size());

		var followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());

		responseObject.setContent(followerList);
		return Response.ok(responseObject).build();
	}

	@DELETE
	@Transactional
	public Response unfollowUser(@PathParam("userId") Long userId, @QueryParam("followerId") Long followerId) {
		var user = userRepository.findById(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		repository.deleteByFollowerAndUser(followerId, userId);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
=======
    @Inject
    FollowerService followerService;

    @PUT
    public Response followUser(@PathParam("userId") Long userId, FollowerRequest request){

        followerService.follow(userId, request);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId){

        FollowersPerUserResponse responseObject = followerService.listAll(userId);
        return Response.ok(responseObject).build();
    }

    @DELETE
    public Response unfollowUser(@PathParam("userId") Long userId, @QueryParam("followerId") Long followerId){
        followerService.unfollow(userId, followerId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
>>>>>>> 6f609a0 (add exceptions)
