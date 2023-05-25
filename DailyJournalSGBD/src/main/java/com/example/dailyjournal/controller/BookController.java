package com.example.dailyjournal.controller;

import com.example.dailyjournal.dto.BookRequestDto;
import com.example.dailyjournal.model.Book;
import com.example.dailyjournal.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getBooks(){
        List<Book> list = bookService.getAll();

        return list;
    }
    @PostMapping("/save")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRequestDto bookRequestDto){
        System.out.println(bookRequestDto.getUserId());
        if(bookRequestDto.getName() == null || bookRequestDto.getAuthor() == null){
            return new ResponseEntity<>("You must complete book title and author!", HttpStatus.BAD_REQUEST);
        }
            Book book = new Book(bookRequestDto.getName(),
                    bookRequestDto.getAuthor(),
                    bookRequestDto.getPrice(),
                    bookRequestDto.getUserId(), bookRequestDto.getFinishDate());
                if(bookService.existsByName(bookRequestDto.getName())){
                    return new ResponseEntity<>("Book with this name already exist!", HttpStatus.BAD_REQUEST);
                }
                bookService.save(book);
                return new ResponseEntity<>("Book saved succesfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteBook(@PathVariable String name) {
        if(bookService.existsByName(name)){
            Book book = bookService.findByName(name);
            bookService.delete(book);
            return new ResponseEntity<>("Book deleted succesfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
    }

}
