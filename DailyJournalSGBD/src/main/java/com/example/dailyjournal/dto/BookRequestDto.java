package com.example.dailyjournal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class BookRequestDto {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Author is required.")

    private String author;
    @NotBlank(message = "Price is required.")

    private String price;
    @NotEmpty(message = "Finished date is required.")

    private LocalDate finish;
    private Long userId;

    public BookRequestDto() {
    }

    public BookRequestDto(String name, String author, String price, LocalDate finish, Long userId) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.finish = finish;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public LocalDate getFinishDate() {
        return finish;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
