package com.smartlock.server.controllers;

import com.smartlock.server.entities.Door;
import com.smartlock.server.entities.DoorInformation;
import com.smartlock.server.entities.DoorKey;
import com.smartlock.server.repositories.DoorKeyRepository;
import com.smartlock.server.repositories.DoorRepository;
import com.smartlock.server.services.DoorKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Door")
@RestController
public class DoorController {
    private final DoorRepository doorRepository;
    private final DoorKeyRepository doorKeyRepository;

    public DoorController(DoorRepository doorRepository, DoorKeyRepository doorKeyRepository) {
        this.doorRepository = doorRepository;
        this.doorKeyRepository = doorKeyRepository;
    }

    @GetMapping("/doors")
    public ResponseEntity<List<DoorInformation>> getAllDoors() {
        List<Door> doors = doorRepository.findAll();
        System.out.println("Dupa");
        List<DoorInformation> doorInformation = new ArrayList<>();
        for(Door door : doors) {
            doorInformation.add(new DoorInformation(door.getDoorID(), door.getDoorName(), door.getKey().getKeyID()));
        }
        return ResponseEntity.ok(doorInformation);
    }

    @GetMapping("/doors/{doorID}/key_val")
    public String getKeyValue(@PathVariable("doorID") int doorId) throws Exception {
        DoorKey key = doorKeyRepository.findOneByDoorKeyId(doorId);
        return DoorKeyService.encryptData(key);
    }

}
