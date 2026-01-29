package com.pfe.smartwallet.model;

import com.pfe.smartwallet.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    // Getters/Setters
}@Entity
public class notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // Getters/Setters
}