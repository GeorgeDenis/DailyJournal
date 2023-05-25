package com.example.dailyjournal.repository;

import com.example.dailyjournal.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Integer> {


    boolean existsByName(String name);

    Book findByName(String name);
}
