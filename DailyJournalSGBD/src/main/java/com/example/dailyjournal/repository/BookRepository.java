package com.example.dailyjournal.repository;

import com.example.dailyjournal.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Integer> {


    boolean existsByName(String name);

    Book findByName(String name);

    List<Book> getAllByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}
