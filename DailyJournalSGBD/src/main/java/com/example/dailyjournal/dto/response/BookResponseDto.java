package com.example.dailyjournal.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public class BookResponseDto {
    private final UUID id;
    private final String name;

    private final String author;

    private final String price;
    private final LocalDate finish;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BookResponseDto(UUID id, String name, String author, String price, LocalDate finish, Long userId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.finish = finish;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
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

    public LocalDate getFinish() {
        return finish;
    }
}
