package com.example.dailyjournal.service;

import com.example.dailyjournal.model.Book;
import com.example.dailyjournal.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }



    public boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }

    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }


    public List<Book> getAllByUserId(Long userId) {
        return bookRepository.getAllByUserId(userId);
    }

    public boolean existsById(int id) {
        return bookRepository.existsById(id);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
}
