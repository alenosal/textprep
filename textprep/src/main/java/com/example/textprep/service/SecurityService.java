package com.example.textprep.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
