package com.git.gabriele.dto;

import java.time.LocalDateTime;

import com.git.gabriele.model.Post;

public class PostResponse {

	private String text;
	private LocalDateTime dateTime;
	
	
	public PostResponse(String text, LocalDateTime dateTime) {
		this.text = text;
		this.dateTime = dateTime;
	}
	
	public String getText() {
		this.text = text;
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public static PostResponse fromEntity (Post post) {s
		var response = new PostResponse();
        response.setText(post.getText());s
        response.setDateTime(post.getDataTime());
        return response;
        }
	
	
}
