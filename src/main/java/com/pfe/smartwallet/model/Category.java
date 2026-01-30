package com.pfe.smartwallet.model;

import jakarta.persistence.*; // Important pour @Entity, @Id, etc.
import java.util.List;

@Entity // <--- C'EST CA QUI MANQUAIT !
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;
    private String type; // "EXPENSE" ou "INCOME"

    // Constructeur vide obligatoire pour JPA
    public Category() {}

    public Category(long l, String transport) {
    }

    // --- GETTERS ET SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}