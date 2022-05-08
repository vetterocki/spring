package com.example.cinema.exceptions.custom;

public class UserWrongPasswordException extends RuntimeException {
    public UserWrongPasswordException(String username) {
        super("Invalid " + username + " password!");
    }
}
