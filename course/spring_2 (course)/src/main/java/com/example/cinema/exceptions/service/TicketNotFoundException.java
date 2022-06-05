package com.example.cinema.exceptions.service;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Could not find ticket by id " + id);
    }
}
