package com.example.application.data.service;

import com.example.application.data.entity.Book;
import com.example.application.data.entity.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    private static final String API_URL = "http://localhost:8082/api/v1/books";
    private final UserService userService;

    public BookService(UserService userService) {
        this.userService = userService;
    }

    public Book[] getBooks() {
        User user = userService.findByIsLoggedTrue();
        if(user == null){
            return new Book[0]; // returneaza un array gol
        }
        Long userId = user.getId();

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?userId=" + userId;
        return restTemplate.getForObject(url, Book[].class);
    }
    public ResponseEntity<String> saveBook(Book book) {
        RestTemplate restTemplate = new RestTemplate();
        User user = userService.findByIsLoggedTrue();
        if (user != null) {
            book.setUserId(user.getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL + "/save", request, String.class);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
