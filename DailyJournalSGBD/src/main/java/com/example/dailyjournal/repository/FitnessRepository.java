package com.example.dailyjournal.repository;

import com.example.dailyjournal.model.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FitnessRepository extends JpaRepository<Fitness,Integer> {
    List<Fitness> getAllByUserId(Long userId);

}
