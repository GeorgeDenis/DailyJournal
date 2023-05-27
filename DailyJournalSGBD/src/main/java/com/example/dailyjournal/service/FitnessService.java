package com.example.dailyjournal.service;

import com.example.dailyjournal.model.Book;
import com.example.dailyjournal.model.Fitness;
import com.example.dailyjournal.repository.BookRepository;
import com.example.dailyjournal.repository.FitnessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class FitnessService {
    private final FitnessRepository fitnessRepository;

    public FitnessService(FitnessRepository fitnessRepository) {
        this.fitnessRepository = fitnessRepository;
    }

    public List<Fitness> getAll() {
        return fitnessRepository.findAll();
    }

    public void save(Fitness fitness) {
        fitnessRepository.save(fitness);
    }


    public void delete(Fitness fitness) {
        fitnessRepository.delete(fitness);
    }


    public List<Fitness> getAllByUserId(Long userId) {
        return fitnessRepository.getAllByUserId(userId);
    }
}
