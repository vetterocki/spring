package com.example.cinema.exceptions.authorization;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User " + username + " already exists!");
    }
}
