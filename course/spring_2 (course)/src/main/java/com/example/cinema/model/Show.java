package com.example.cinema.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @OneToMany(mappedBy = "show", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ticket> ticket;

    @Min(value = 1, message = "Minutes amount can not be less than 1!")
    @Max(value = 600, message = "Minutes amount can not be bigger than 600!")
    @NotNull(message = "Minutes must be defined!")
    private Integer minutes;

    @Min(value = 0, message = "Seats amount can not be less than 0!")
    @Max(value = 200, message = "Seats amount can not be bigger than 200!")
    @NotNull(message = "Seats amount must be defined!")
    private Integer seatsAmount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Date must be defined!")
    private LocalDateTime date;

    @NotBlank(message = "Movie name cannot be empty!")
    private String movieName;

    public Show(int minutes, int seatsAmount, String movieName) {
        this.minutes = minutes;
        this.seatsAmount = seatsAmount;
        this.movieName = movieName;
    }

}
