package com.example.application.data.service;

import com.example.application.data.entity.Book;
import com.example.application.data.entity.Fitness;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
    @Service
    public class FitnessService {
        private static final String API_URL = "http://localhost:8082/api/v1/fitness";
        private final UserService userService;


        public FitnessService(UserService userService) {
            this.userService = userService;
        }

        public Fitness[] getFitness() {
            User user = userService.findByIsLoggedTrue();
            if(user == null){
                return new Fitness[0]; // returneaza un array gol
            }
            Long userId = user.getId();

            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + "?userId=" + userId;
            return restTemplate.getForObject(url, Fitness[].class);
        }
        public ResponseEntity<String> saveFitness(Fitness fitness) {
            RestTemplate restTemplate = new RestTemplate();
            User user = userService.findByIsLoggedTrue();
            if (user != null) {
                fitness.setUserId(user.getId());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Fitness> request = new HttpEntity<>(fitness, headers);

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(API_URL + "/save", request, String.class);
                return response;
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        public ResponseEntity<String> deleteFitness(int id) {
            RestTemplate restTemplate = new RestTemplate();

            try {
                String url = API_URL + "/" + id;
                restTemplate.delete(url);
                return new ResponseEntity<>("Fitness entry deleted succesfully", HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>("Fitness entry not found", HttpStatus.NOT_FOUND);
            }
        }
        public ResponseEntity<String> updateFitness(Fitness fitness) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Fitness> request = new HttpEntity<>(fitness, headers);

            try {
                String url = API_URL + "/" + fitness.getId();
                restTemplate.put(url, request);
                return new ResponseEntity<>("Fitness entry updated successfully", HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>("Fitness entry not found", HttpStatus.NOT_FOUND);
            }
        }



    }