package com.git.gabriele.dto;

public class FollowerRequest {
	private Long followerId;

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public FollowerRequest(Long followerId) {
		this.followerId = followerId;
	}
}
