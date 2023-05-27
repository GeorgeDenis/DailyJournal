package com.example.dailyjournal.dto.request;

import java.time.LocalDate;

public class FitnessRequestDto {
    private String gym;

    private Double water;
    private Double sleep;
    private Double calories;
    private Long userId;
    private LocalDate Date;

    public FitnessRequestDto() {
    }

    public FitnessRequestDto(String gym, Double water, Double sleep, Double calories, Long userId) {
        this.gym = gym;
        this.water = water;
        this.sleep = sleep;
        this.calories = calories;
        this.userId = userId;
        Date = LocalDate.now();
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getSleep() {
        return sleep;
    }

    public void setSleep(Double sleep) {
        this.sleep = sleep;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }
}

