package com.example.cinema.exceptions.service;

import com.example.cinema.model.Show;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(int row, int number, Show show) {
        super("Could not find seat №" + number + " in a row №" + row + " in " + show.getMovieName());
    }

}
