package com.example.cinema.service;

import com.example.cinema.exceptions.service.SeatNotFoundException;
import com.example.cinema.exceptions.service.ShowNotFoundException;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Show;
import com.example.cinema.repository.SeatRepository;
import com.example.cinema.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public SeatService(SeatRepository seatRepository, ShowRepository showRepository) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public List<Seat> findAllByShowId(Long showId) {
        return seatRepository.findAllByShowId(showId);
    }

    public Seat findByShow(Long showId, int row, int number) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException(showId));
        return seatRepository.findByShowIdAndNumberAndRowNumber(showId, number, row)
                .orElseThrow(() -> new SeatNotFoundException(row, number, show));
    }

    public void save(Seat seat) {
        seatRepository.save(seat);
    }

    public void delete(Seat seat) {
        seatRepository.delete(seat);
    }
}
