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
@Table(name="door_key")
public class DoorKey {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyID;

    @Column(name = "keyValue", length = 2000)
    private String keyValue;
}
