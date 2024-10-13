package com.smartlock.server.controllers;

import com.smartlock.server.entities.Door;
import com.smartlock.server.entities.User;
import com.smartlock.server.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.smartlock.server.repositories.DoorRepository;

import java.util.List;

@Tag(name = "User")
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final DoorRepository doorRepository;

    public UserController(UserRepository userRepository, DoorRepository doorRepository) {
        this.userRepository = userRepository;
        this.doorRepository = doorRepository;
    }

    @GetMapping("/users")
    public List<User> getUsersInfo() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}/info")
    public User getUser(@PathVariable("id") int id) {
        return userRepository.findUserByLogin(id);
    }

    @GetMapping("/users/{id}/doors")
    public List<Door> getAllKeys(@PathVariable("id") int id) {
        return doorRepository.findAllByUserId(id);
    }

}