package com.example.cinema.exceptions.custom;

public class InvalidParamsException extends RuntimeException {
    public InvalidParamsException(String username, String password) {
        super("Invalid params: " + username + " or " + password);
    }

}
