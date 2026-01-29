package com.pfe.smartwallet.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // Import essentiel !
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal balance;
    private String currency;

    // --- RELATIONS ---

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // <--- Coupe la boucle Wallet -> User -> Wallet...
    private User user;

    // On GARDE la liste des transactions ici (pas de JsonIgnore)
    // car quand on demande le Wallet, on veut voir ses transactions.
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // --- CONSTRUCTEURS ---
    public Wallet() {}

    // --- GETTERS ET SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}