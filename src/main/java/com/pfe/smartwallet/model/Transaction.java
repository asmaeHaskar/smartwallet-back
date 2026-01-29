package com.pfe.smartwallet.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // <--- CHANGE L'IMPORT ICI
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String note;
    private LocalDateTime date = LocalDateTime.now();
    private String type;

    // --- CORRECTION ICI ---
    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    // On n'utilise PLUS @JsonIgnore (qui bloque tout)
    // On utilise ça pour dire : "Charge le Wallet, mais ignore sa liste de transactions à lui"
    @JsonIgnoreProperties("transactions")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // ... tes constructeurs ...
    public Transaction() {}

    public Transaction(BigDecimal amount, String note, Wallet wallet, Category category, String type) {
        this.amount = amount;
        this.note = note;
        this.wallet = wallet;
        this.category = category;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    // ... tes getters et setters (ne changent pas) ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Wallet getWallet() { return wallet; }
    public void setWallet(Wallet wallet) { this.wallet = wallet; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}