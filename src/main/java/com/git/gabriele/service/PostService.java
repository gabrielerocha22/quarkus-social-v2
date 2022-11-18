package com.git.gabriele.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.git.gabriele.dto.CreatePostRequest;
import com.git.gabriele.dto.PostResponse;
import com.git.gabriele.exception.FollowsException;
import com.git.gabriele.exception.NoFollowerException;
import com.git.gabriele.exception.NotFoundUserException;
import com.git.gabriele.model.Post;
import com.git.gabriele.model.User;
import com.git.gabriele.repositoriy.FollowerRepository;
import com.git.gabriele.repositoriy.PostRepository;
import com.git.gabriele.repositoriy.UserRepository;

import io.quarkus.panache.common.Sort;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostService {

    public static final String MESSAGE_FORGOT_FOLLOWER_HEADER = "You forgot the header followerId";
    public static final String MESSAGE_FOLLOWER_NOT_FOUND = "Follower Not Found";
    public static final String MESSAGE_FOLLOWS = "You can't see these posts";

    public static final String MESSAGE_USER_NOT_FOUND = "User Not Found";

    @Inject
    UserRepository userRepository;
    @Inject
    PostRepository repository;
    @Inject
    FollowerRepository followerRepository;

    @Transactional
    public void save(Long userId, CreatePostRequest request){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }
        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        repository.persist(post);
    }

    public List<PostResponse> listAllPosts(Long userId, Long followerId){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new NotFoundUserException(MESSAGE_USER_NOT_FOUND);
        }

        if(followerId == null){
            throw new NoFollowerException(MESSAGE_FORGOT_FOLLOWER_HEADER);
        }

        User follower = userRepository.findById(followerId);
        if(follower == null){
            throw new NoFollowerException(MESSAGE_FOLLOWER_NOT_FOUND);
        }

        boolean follows = followerRepository.follows(follower, user);
        if(!follows){
            throw new FollowsException(MESSAGE_FOLLOWS);
        }
        var query = repository.
                find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();

        var postResponseList = list.stream().map(post -> PostResponse.fromEntity(post)).
                collect(Collectors.toList());
        return postResponseList;
    }
}