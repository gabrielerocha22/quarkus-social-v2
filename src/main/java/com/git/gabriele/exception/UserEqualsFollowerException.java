package com.git.gabriele.exception;

public class UserEqualsFollowerException extends RuntimeException {

    public UserEqualsFollowerException(String message) {
        super(message);
    }

    public UserEqualsFollowerException() {
    }
}