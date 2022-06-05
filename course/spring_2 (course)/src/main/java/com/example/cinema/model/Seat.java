package com.example.cinema.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "show_id")
    @NotNull
    private Show show;

    @ToString.Exclude
    @OneToMany(mappedBy = "seat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ticket> ticket;

    @Min(value = 1, message = "Row number can not be < 1!")
    @Max(value = 20, message = "Row number can not be > 20!")
    private int rowNumber;

    @Min(value = 1, message = "Number can not be < 1!")
    @Max(value = 10, message = "Number can not be > 20!")
    private int number;

    private boolean reserved;


    public Seat(Show show, int rowNumber, int number) {
        this.show = show;
        this.rowNumber = rowNumber;
        this.number = number;
        this.reserved = false;
    }

}
