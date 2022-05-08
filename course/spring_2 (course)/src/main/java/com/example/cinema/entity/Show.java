package com.example.cinema.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Table(name = "SHOWS")
public class Show {
    private Long id;

    @NotNull
    @Min(45)
    private int minutes;

    @NotNull
    @NotBlank
    @Min(3)
    private String movieName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public Show(int minutes, String movieName) {
        this.minutes = minutes;
        this.movieName = movieName;
    }
}
