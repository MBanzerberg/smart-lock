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
@Table(name="door")
public class Door {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doorID;

    @Column(name="doorName", length = 30)
    private String doorName;

    @ManyToOne @JoinColumn(name = "keyId")
    private DoorKey key;
}
