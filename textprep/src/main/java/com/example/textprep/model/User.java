package com.example.textprep.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private final String mail;
    private final Role role;

    public User(UUID id, String name, String mail, Role role) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
