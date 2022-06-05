package com.example.cinema.exceptions.service;

import com.example.cinema.model.Show;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException(int row, int number, Show show) {
        super("Seat №" + number + " in a row №" + row + " in " + show.getMovieName() + " is already reserved!");
    }
}
