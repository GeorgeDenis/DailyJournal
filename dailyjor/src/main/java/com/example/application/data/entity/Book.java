package com.example.application.data.entity;


import java.time.LocalDate;

public class Book {
    private int id;
    private String name;

    private String author;

    private double price;
    private LocalDate finish;
    private Long userId;

    public Book() {
    }

    public Book( String name, String author, double price, LocalDate finish, Long userId) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.finish = finish;
        this.userId = userId;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

