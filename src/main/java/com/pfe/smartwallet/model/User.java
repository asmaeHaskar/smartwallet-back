package com.pfe.smartwallet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "users")
@Data // Génère automatiquement les Getters et Setters pour tous les champs
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    // Renommé pour correspondre à password_hash dans ta base de données
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    // Supprime les méthodes vides manuelles, @Data s'occupe de tout maintenant !
}