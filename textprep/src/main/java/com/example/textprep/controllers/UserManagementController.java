package com.example.textprep.controllers;


import com.example.textprep.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Alek"),
            new User(2, "Dawid")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewUser(@RequestBody User user) {
        System.out.println("registerNewStudent");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("Delete Student");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateStudent(@PathVariable("userId")Integer userId, User user) {
        System.out.println(String.format("%s %s", userId, user));
    }
}
