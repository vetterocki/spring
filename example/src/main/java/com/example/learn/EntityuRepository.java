package com.example.learn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityuRepository extends JpaRepository<Something, Long> {
}
