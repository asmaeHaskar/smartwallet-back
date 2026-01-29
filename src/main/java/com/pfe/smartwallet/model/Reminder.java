package com.pfe.smartwallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal amount;
    private LocalDateTime alertDate; // La date/heure de l'alarme
    private boolean active;

    // On lie le rappel à un utilisateur (pour ne pas voir les rappels des autres)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructeur vide obligatoire
    public Reminder() {}

    public Reminder(String title, BigDecimal amount, LocalDateTime alertDate, User user) {
        this.title = title;
        this.amount = amount;
        this.alertDate = alertDate;
        this.user = user;
        this.active = true;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDateTime getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDateTime alertDate) { this.alertDate = alertDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}