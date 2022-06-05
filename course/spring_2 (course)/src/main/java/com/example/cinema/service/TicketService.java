package com.example.cinema.service;

import com.example.cinema.exceptions.authorization.UserNotFoundException;
import com.example.cinema.exceptions.service.SeatAlreadyReservedException;
import com.example.cinema.exceptions.service.SeatNotFoundException;
import com.example.cinema.exceptions.service.TicketNotFoundException;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Show;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.TicketRepository;
import com.example.cinema.security.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final ShowService showService;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, SeatService seatService,
                         ShowService showService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.showService = showService;
        this.userService = userService;
    }

    public Seat getSeatRequest(Show show, int row, int number) {
        List<Seat> seats = seatService.findAllByShowId(show.getId());
        Seat seat = null;
        for (Seat temp : seats) {
            if (temp.getNumber() == number && temp.getRowNumber() == row) {
                seat = temp;
            }
        }
        return Optional.ofNullable(seat)
                .orElseThrow(() -> new SeatNotFoundException(row, number, show));
    }

    public void save(Long showId, int row, int number, String username) {
        Show show = showService.findById(showId);
        Seat seat = getSeatRequest(show, row, number);
        User user = userService.findUserByUsername(username);
        saveTicket(seat, show, row, number, user);
    }

    public void save(Long showId, int row, int number, User user) {
        Show show = showService.findById(showId);
        Seat seat = getSeatRequest(show, row, number);
        if (!userService.existsByUsername(user.getUsername())) {
            throw new UserNotFoundException(user.getUsername());
        }
        saveTicket(seat, show, row, number, user);
    }

    public void saveTicket(Seat seat, Show show, int row, int number, User user) {
        if (seat.isReserved()) {
            throw new SeatAlreadyReservedException(row, number, show);
        } else {
            seat.setReserved(true);
            show.setSeatsAmount(show.getSeatsAmount() - 1);
            seatService.save(seat);

            Ticket ticket = new Ticket(seat, show, user);
            ticketRepository.save(ticket);
        }
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllByUserId(Long id) {
        return ticketRepository.findAllByUserId(id);
    }

    public void deleteById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        ticketRepository.deleteById(ticket.getId());
    }

    public List<Ticket> findByString(String str) {
        List<Ticket> list = ticketRepository.findAll();
        return list.stream()
                .filter(ticket -> ticket.getShow().getMovieName()
                        .toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public void change(Long id, Long showId, int row, int number) {
        Show show = showService.findById(showId);
        Seat seat = seatService.findByShow(showId, row, number);

        if (seat.isReserved())
            throw new SeatAlreadyReservedException(seat.getRowNumber(), seat.getNumber(), show);

        ticketRepository.findById(id)
                .map(newTicket -> {
                    newTicket.setSeat(seat);
                    newTicket.setShow(show);
                    return ticketRepository.save(newTicket);
                })
                .orElseThrow(() -> new TicketNotFoundException(id));


    }
}












