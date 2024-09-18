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
@Table(name = "password")
public class Password {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passwordID;

    @Column(name="password", length = 100)
    private String password;
}
