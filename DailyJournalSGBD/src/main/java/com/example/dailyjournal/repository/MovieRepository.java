package com.example.dailyjournal.repository;

import com.example.dailyjournal.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    boolean existsByName(String name);

    Movie findByName(String name);

    List<Movie> getAllByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}
