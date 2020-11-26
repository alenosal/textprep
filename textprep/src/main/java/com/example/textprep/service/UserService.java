package com.example.textprep.service;

import com.example.textprep.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
