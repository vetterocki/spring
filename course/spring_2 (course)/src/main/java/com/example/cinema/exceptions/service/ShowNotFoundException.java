package com.example.cinema.exceptions.service;


public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(Long id) {
        super("Could not found show by id " + id);
    }
}
