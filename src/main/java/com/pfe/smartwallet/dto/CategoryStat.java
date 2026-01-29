package com.pfe.smartwallet.dto;

import java.math.BigDecimal;

public interface CategoryStat {
    String getCategoryName();
    BigDecimal getTotalAmount();
    String getColor(); // Pour le graphique
}