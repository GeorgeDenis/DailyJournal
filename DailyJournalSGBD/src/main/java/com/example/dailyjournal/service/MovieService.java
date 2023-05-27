package com.example.dailyjournal.service;

import com.example.dailyjournal.model.Movie;
import com.example.dailyjournal.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }



    public boolean existsByName(String name) {
        return movieRepository.existsByName(name);
    }

    public Movie findByName(String name) {
        return movieRepository.findByName(name);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }


    public List<Movie> getAllByUserId(Long userId) {
        return movieRepository.getAllByUserId(userId);
    }

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public boolean existsById(int id) {
        return movieRepository.existsById(id);
    }
}
