package com.example.cinema.service;

import com.example.cinema.exceptions.service.IllegalLocalDateTimeException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocalDateTimeService {
    public boolean checkIfTimeValid(LocalDateTime someTime) {
        LocalDateTime timeNow = LocalDateTime.now();
        return timeNow.isEqual(someTime) || timeNow.isBefore(someTime);
    }

    public void validateDateTime(LocalDateTime someTime) {
        if (!checkIfTimeValid(someTime))
            throw new IllegalLocalDateTimeException(someTime);
    }



}
