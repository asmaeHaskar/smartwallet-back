package com.pfe.smartwallet.dto;

import java.math.BigDecimal;
import lombok.Data; // Lombok génère les Getters/Setters

@Data
public class TransactionRequestDTO {
    private BigDecimal amount;
    private String note;
    private Long walletId;   // L'ID du portefeuille (ex: 1)
    private Long categoryId; // L'ID de la catégorie (ex: 2)
    private String type;     // "EXPENSE" ou "INCOME"
}