package com.example.textprep.entity;

public class GroupDao {

    String name;
    String userId;

    public GroupDao(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
