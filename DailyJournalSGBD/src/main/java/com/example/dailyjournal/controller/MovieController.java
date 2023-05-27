package com.example.dailyjournal.controller;

import com.example.dailyjournal.dto.request.MovieRequestDto;
import com.example.dailyjournal.model.Movie;
import com.example.dailyjournal.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<Movie> getMovies(@RequestParam Long userId){
        System.out.println(userId);

        return movieService.getAllByUserId(userId);
    }
    @PostMapping("/save")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MovieRequestDto movieRequestDto){
        System.out.println(movieRequestDto.getUserId());
        if(movieRequestDto.getName() == null || movieRequestDto.getRating() == null){
            return new ResponseEntity<>("You must complete movie title and rating!", HttpStatus.BAD_REQUEST);
        }
        Movie movie = new Movie(movieRequestDto.getName(),
                movieRequestDto.getRating(),
                movieRequestDto.getUserId(), movieRequestDto.getFinish());
        if(movieService.existsByName(movieRequestDto.getName())){
            return new ResponseEntity<>("Movie with this name already exist!", HttpStatus.BAD_REQUEST);
        }
        movieService.save(movie);
        return new ResponseEntity<>("Movie saved succesfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteMovie(@PathVariable String name) {
        if(movieService.existsByName(name)){
            Movie movie = movieService.findByName(name);
            movieService.delete(movie);
            return new ResponseEntity<>("Movie deleted succesfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Movie not found",HttpStatus.NOT_FOUND);
    }

}
