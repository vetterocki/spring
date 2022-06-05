package com.example.cinema.security.model;

import com.example.cinema.model.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username")
    @NotBlank(message = "Username can not be empty!")
    @Size(min = 3, max = 24, message = "Username can not be less than 3 or bigger than 24!")
    private String username;


    @Column(name = "password")
    @NotBlank(message = "Password can not be empty!")
    @Size(min = 3, message = "Password can not be less than 3 or bigger than 24!")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ticket> tickets;

    @Column(name = "role")
    private String role;

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
