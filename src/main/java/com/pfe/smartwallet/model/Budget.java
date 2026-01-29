package com.pfe.smartwallet.model;

import jakarta.persistence.*; // Indispensable
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // <--- La clé pour éviter l'erreur "Unknown entity"
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal limitAmount; // Le plafond (ex: 1000 DH)
    private LocalDate startDate;
    private LocalDate endDate;

    // Qui a créé ce budget ?
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Sur quelle catégorie porte ce budget ?
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // --- CONSTRUCTEUR VIDE (Obligatoire) ---
    public Budget() {}

    // --- GETTERS ET SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}