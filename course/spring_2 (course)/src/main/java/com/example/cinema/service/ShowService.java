package com.example.cinema.service;


import com.example.cinema.exceptions.service.ShowNotFoundException;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Show;
import com.example.cinema.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class  ShowService {
    private final ShowRepository showRepository;
    private final SeatService seatService;
    private final LocalDateTimeService localDateTimeService;

    public ShowService(ShowRepository showRepository, SeatService seatService, LocalDateTimeService localDateTimeService) {
        this.showRepository = showRepository;
        this.seatService = seatService;
        this.localDateTimeService = localDateTimeService;
    }

    public void generateSeatList(Show show, int seatsAmount, int row, int number) {
        for (int temp = 1; temp <= seatsAmount; temp++, number++) {
            Seat seat = new Seat(show, row, number);
            seatService.save(seat);
            if (number == 10) {
                row++;
                number = 0;
            }
        }
    }

    public void save(Show show) {
        localDateTimeService.validateDateTime(show.getDate());
        generateSeatList(show, show.getSeatsAmount(), 1, 1);
        showRepository.save(show);
    }

    public Show findById(Long showId) {
        return showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException(showId));
    }

    public List<Show> findAll() {
        return showRepository.findAll();
    }

    public void deleteById(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException(id));
        showRepository.delete(show);
    }

    public List<Show> findByString(String str) {
        List<Show> list = showRepository.findAll();
        return list.stream()
                .filter(show -> show.getMovieName().toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public void change(Show show) {
        Show showBefore = findById(show.getId());
        List<Seat> seats = seatService.findAllByShowId(show.getId());
        localDateTimeService.validateDateTime(show.getDate());

        int amountBefore = showBefore.getSeatsAmount();
        int amount = show.getSeatsAmount();

        if (amountBefore < amount) {
            Seat lastSeat = seats.get(seats.size() - 1);
            generateSeatList(showBefore, amount - amountBefore,
                    lastSeat.getRowNumber(), lastSeat.getNumber() + 1);
        } else if (amountBefore > amount) {
            IntStream.iterate(seats.size() - 1, i -> i >= show.getSeatsAmount(), i -> i - 1)
                    .mapToObj(seats::get).forEach(seatService::delete);
        }

        showBefore.setId(show.getId());
        showBefore.setDate(show.getDate());
        showBefore.setMinutes(show.getMinutes());
        showBefore.setSeatsAmount(show.getSeatsAmount());
        showBefore.setMovieName(show.getMovieName());

        showRepository.save(showBefore);

    }

}
