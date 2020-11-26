package com.example.textprep.repository;

import com.example.textprep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);

}
