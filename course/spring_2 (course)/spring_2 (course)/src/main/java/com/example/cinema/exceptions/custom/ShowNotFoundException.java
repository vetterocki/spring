package com.example.cinema.exceptions.custom;


public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String movieName) {
        super("Could not found show " + movieName);
    }
}
