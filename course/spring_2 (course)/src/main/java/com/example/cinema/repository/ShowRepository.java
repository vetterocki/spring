package com.example.cinema.repository;

import com.example.cinema.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findByMovieName(String movieName);
    Optional<Show> deleteByMovieName(String movieName);
}
