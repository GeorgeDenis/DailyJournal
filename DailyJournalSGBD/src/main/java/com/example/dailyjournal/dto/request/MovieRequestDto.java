package com.example.dailyjournal.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class MovieRequestDto {
    @NotBlank(message = "Name is required.")
    private String name;

    private Double rating;
    @NotEmpty(message = "Finished date is required.")

    private LocalDate finish;
    private Long userId;

    public MovieRequestDto() {
    }

    public MovieRequestDto(String name, Double rating, LocalDate finish, Long userId) {
        this.name = name;
        this.rating = rating;
        this.finish = finish;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

