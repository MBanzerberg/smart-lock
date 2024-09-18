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
@Table(name="User")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "username", length = 50)
    private String username;

    @ManyToOne @JoinColumn(name = "passwordId")
    private Password password;
}
