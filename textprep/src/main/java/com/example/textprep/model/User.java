package com.example.textprep.model;

public class User {
    private final Integer userId;
    private final String name;

    public User(Integer id, String name) {
        this.userId = id;
        this.name = name;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }
}
