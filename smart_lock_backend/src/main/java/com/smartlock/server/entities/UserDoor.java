package com.smartlock.server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_door")
public class UserDoor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userDoorID;

    @ManyToOne @JoinColumn(name = "userId")
    private User user;

    @ManyToOne @JoinColumn(name = "doorId")
    private Door door;
}
