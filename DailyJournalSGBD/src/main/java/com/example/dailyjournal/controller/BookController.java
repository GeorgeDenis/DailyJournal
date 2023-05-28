package com.example.dailyjournal.controller;

import com.example.dailyjournal.dto.request.BookRequestDto;
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
    public List<Book> getBooks(@RequestParam Long userId){
        System.out.println(userId);

        return bookService.getAllByUserId(userId);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        if(bookService.existsById(id)){
            Book book = bookService.findById(id);
            bookService.delete(book);
            return new ResponseEntity<>("Book deleted succesfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@Valid @RequestBody BookRequestDto bookRequestDto,@PathVariable int id) {
        if(bookService.existsById(id)){
            Book book = bookService.findById(id);
            if(!bookRequestDto.getAuthor().isEmpty()){
                book.setAuthor(bookRequestDto.getAuthor());
            }
            if(!bookRequestDto.getName().isEmpty()){
                book.setName(bookRequestDto.getName());
            }
            if(bookRequestDto.getFinish() != null){
                book.setFinish(bookRequestDto.getFinish());
            }
            if(!bookRequestDto.getPrice().equals("0.0")){
                book.setPrice(bookRequestDto.getPrice());
            }
            bookService.save(book);
            return new ResponseEntity<>("Book updated succesfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
    }

}
