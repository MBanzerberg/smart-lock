package com.smartlock.server.controllers;

import com.smartlock.server.entities.User;
import com.smartlock.server.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsersInfo() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}/info")
    public User getUser(@PathVariable("id") int id) {
        return userRepository.findUserByLogin(id);
    }
}