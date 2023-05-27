package com.example.dailyjournal.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fitness {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String gym;
    private Double water;
    private Double sleep;
    private Double calories;
    private Long userId;

    private LocalDate Date;

    public Fitness() {
    }

    public Fitness(String gym, Double water, Double sleep, Double calories, Long userId, LocalDate Date) {
        this.gym = gym;
        this.water = water;
        this.sleep = sleep;
        this.calories = calories;
        this.userId = userId;
        this.Date = Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
