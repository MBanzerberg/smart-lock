package com.smartlock.server.controllers;

import com.smartlock.server.entities.*;
import com.smartlock.server.repositories.DoorKeyRepository;
import com.smartlock.server.repositories.UserRepository;
import com.smartlock.server.services.DoorKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.smartlock.server.repositories.DoorRepository;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final DoorRepository doorRepository;
    private final DoorKeyRepository doorKeyRepository;

    public UserController(UserRepository userRepository, DoorRepository doorRepository, DoorKeyRepository doorKeyRepository) {
        this.userRepository = userRepository;
        this.doorRepository = doorRepository;
        this.doorKeyRepository = doorKeyRepository;
    }

    @GetMapping
    public List<User> getUsersInfo() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUser(@PathVariable("id") int id) {
        return userRepository.findUserByLogin(id);
    }

    /*@PostMapping("/login")
    public void postMessage(@RequestBody LoginMessage loginMessage) {
        System.out.println(loginMessage.getUsername());
        System.out.println(loginMessage.getPassword());
    }*/

    @GetMapping("/{id}/doors")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public List<DoorInformation> getAllKeys(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findUserByUsername(userDetails.getUsername());
        List<DoorInformation> doorInformations = new ArrayList<>();
        List<Door> doors = doorRepository.findAllByUserId(user.getUserID());

        for (Door door : doors) {
            doorInformations.add(new DoorInformation(door.getDoorID(), door.getDoorName(), door.getKey().getKeyID()));
        }

        return doorInformations;
    }

    @GetMapping("/{id}/doors/{doorID}/key_val")
    public String getUserKeyValue(@PathVariable("doorID") int doorId, @PathVariable("id") int userID) throws Exception {
        DoorKey key = doorKeyRepository.findOneByDoorKeyId(doorId);
        return DoorKeyService.encryptData(key);
    }
}