package com.pfe.smartwallet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // En production, utilise BCrypt pour hacher

    private String fullName;

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    public void setPasswordHash(String s) {
    }

    public Object getPasswordHash() {
        return null;
    }
}