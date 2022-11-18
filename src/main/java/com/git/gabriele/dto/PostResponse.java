package com.git.gabriele.dto;

import java.time.LocalDateTime;

import com.git.gabriele.model.Post;

public class PostResponse {

	private String text;
	private LocalDateTime dateTime;
<<<<<<< HEAD
	
	
//	public PostResponse(String text, LocalDateTime dateTime) {
//		this.text = text;
//		this.dateTime = dateTime;
//	}
	
=======

>>>>>>> 6f609a0 (add exceptions)
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
<<<<<<< HEAD
	
=======
>>>>>>> 6f609a0 (add exceptions)
	public static PostResponse fromEntity (Post post) {
		var response = new PostResponse();
        response.setText(post.getText());
        response.setDateTime(post.getDataTime());
        return response;
        }
<<<<<<< HEAD
	
	
=======
>>>>>>> 6f609a0 (add exceptions)
}
