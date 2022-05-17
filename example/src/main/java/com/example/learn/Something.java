package com.example.learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Something {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Example example;

    private int minutes;

    public Something(int minutes) {
        this.minutes = minutes;
    }
}
