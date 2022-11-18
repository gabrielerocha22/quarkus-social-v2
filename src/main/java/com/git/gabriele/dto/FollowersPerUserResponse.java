package com.git.gabriele.dto;

import java.util.List;

public class FollowersPerUserResponse {
	
	private Integer followersCount;
	
	private List <FollowerResponse> content;

	public Integer getFollowersCount() {
		return followersCount;
	}
<<<<<<< HEAD

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public List<FollowerResponse> getContent() {
		return content;
	}

	public void setContent(List<FollowerResponse> content) {
		this.content = content;
	}

	
=======
	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}
	public List<FollowerResponse> getContent() {
		return content;
	}
	public void setContent(List<FollowerResponse> content) {
		this.content = content;
	}
>>>>>>> 6f609a0 (add exceptions)
}
