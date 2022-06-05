package com.example.cinema.exceptions.service;

import java.time.LocalDateTime;

public class IllegalLocalDateTimeException extends RuntimeException {
    public IllegalLocalDateTimeException(LocalDateTime localDateTime) {
        super(localDateTime + " cannot be set, because it represents a date which is already gone!");
    }
}
