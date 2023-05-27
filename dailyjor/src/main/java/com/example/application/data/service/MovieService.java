package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {
    private static final String API_URL = "http://localhost:8082/api/v1/movies";
    private final UserService userService;


    public MovieService(UserService userService) {
        this.userService = userService;
    }

    public Movie[] getMovies() {
        User user = userService.findByIsLoggedTrue();
        if(user == null){
            return new Movie[0]; // returneaza un array gol
        }
        Long userId = user.getId();

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?userId=" + userId;
        return restTemplate.getForObject(url, Movie[].class);
    }
    public ResponseEntity<String> saveMovie(Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        User user = userService.findByIsLoggedTrue();
        if (user != null) {
            movie.setUserId(user.getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL + "/save", request, String.class);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteMovie(int id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String url = API_URL + "/" + id;
            restTemplate.delete(url);
            return new ResponseEntity<>("Movie deleted succesfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }



}
