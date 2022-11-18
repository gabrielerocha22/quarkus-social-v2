package com.git.gabriele.dto;

import com.git.gabriele.model.Follower;

public class FollowerResponse {

	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FollowerResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}
<<<<<<< HEAD
	
	public FollowerResponse() {
		
	}
	
=======
	public FollowerResponse() {	
	}
>>>>>>> 6f609a0 (add exceptions)
	public FollowerResponse(Follower follower) {
		this(follower.getId(), follower.getFollower().getName());
	}
}
