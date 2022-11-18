package com.git.gabriele.service;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.git.gabriele.dto.FollowerRequest;
import com.git.gabriele.dto.FollowerResponse;
import com.git.gabriele.dto.FollowersPerUserResponse;
import com.git.gabriele.exception.NoFollowerException;
import com.git.gabriele.exception.NotFoundUserException;
import com.git.gabriele.exception.UserEqualsFollowerException;
import com.git.gabriele.model.User;
import com.git.gabriele.model.Follower;
import com.git.gabriele.repositoriy.FollowerRepository;
import com.git.gabriele.repositoriy.UserRepository;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerService {

    public static final String MESSAGE_FOLLOWER_NOT_FOUND = "Follower Not Found";
    public static final String MESSAGE_USER_NOT_FOUND = "User Not Found";

    @Inject
    FollowerRepository repository;

    @Inject
    UserRepository userRepository;
    @Transactional
    public void follow(Long userId, FollowerRequest request){

        if(userId.equals(request.getFollowerId())){
            throw new UserEqualsFollowerException("You can't follow yourself!");
        }

        User user = (User) userRepository.findById(userId);
        if(user == null){
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
        var follower = userRepository.findById(request.getFollowerId());
        if(follower == null) {
            throw new NoFollowerException(MESSAGE_FOLLOWER_NOT_FOUND);
        }
        boolean follows = repository.follows(follower, user);

        if(!follows){
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);
            repository.persist(entity);
        }
    }
    public FollowersPerUserResponse listAll(Long userId){

        User user = userRepository.findById(userId);
        if(user == null){
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
        var list = repository.findByUser(userId);

        FollowersPerUserResponse responseObject = new FollowersPerUserResponse();
        responseObject.setFollowersCount(list.size());
        var followersList = list.stream().map(FollowerResponse::new).
                collect(Collectors.toList());
        responseObject.setContent(followersList);
        return responseObject;
    }

    @Transactional
    public void unfollow(Long userId, Long followerId){
        User user = userRepository.findById(userId);
        if(user == null){
           throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
        repository.deleteByFollowerAndUser(followerId, userId);
    }
}

