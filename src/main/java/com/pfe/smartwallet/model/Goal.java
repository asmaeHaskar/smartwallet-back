package com.pfe.smartwallet.model;

import com.pfe.smartwallet.model.User;
import jakarta.persistence.*;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double targetAmount;
    private Double currentAmount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // Getters/Setters
}