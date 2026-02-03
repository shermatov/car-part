package com.shermatov.carparts.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
}
