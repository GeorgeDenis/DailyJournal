package com.example.dailyjournal.repository;

import com.example.dailyjournal.model.Book;
import com.example.dailyjournal.model.Fitness;
import com.example.dailyjournal.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Book> getAllByUserId(Long userId);
}
