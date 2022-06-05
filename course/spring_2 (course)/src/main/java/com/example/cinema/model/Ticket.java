package com.example.cinema.model;

import com.example.cinema.security.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Ticket(Seat seat, Show show) {
        this.seat = seat;
        this.show = show;
    }

    public Ticket(Seat seat, Show show, User user) {
        this.seat = seat;
        this.show = show;
        this.user = user;
    }
}
