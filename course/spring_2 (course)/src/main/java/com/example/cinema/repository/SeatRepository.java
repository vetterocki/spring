package com.example.cinema.repository;

import com.example.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByShowIdAndNumberAndRowNumber(Long showId, int number, int row);
    List<Seat> findAllByShowId(Long showId);
}
