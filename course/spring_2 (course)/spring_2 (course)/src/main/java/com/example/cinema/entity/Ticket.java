package com.example.cinema.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
/**
 *
 * to design entity in entity usage later
 *
 *
 **/
/*
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket {
    private Long id;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @OneToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @NotNull
    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public Ticket(double price, Show show, Seat seat) {
        this.price = price;
        this.show = show;
        this.seat = seat;
    }
} */