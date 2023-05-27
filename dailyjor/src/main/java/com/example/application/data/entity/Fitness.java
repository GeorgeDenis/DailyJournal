package com.example.application.data.entity;

import java.time.LocalDate;

public class Fitness {

    private String gym;
    private String water;
    private Double sleep;
    private Double calories;
    private Long userId;
    private LocalDate Date;

    public Fitness() {
    }


    public Fitness(String gym, String water, Double sleep, Double calories, Long userId, LocalDate date) {
        this.gym = gym;
        this.water = water;
        this.sleep = sleep;
        this.calories = calories;
        this.userId = userId;
        Date = date;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
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
