package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.dto.CategoryStat;
import com.pfe.smartwallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Cette requête magique groupe les dépenses par catégorie
    @Query("SELECT c.name as categoryName, SUM(t.amount) as totalAmount " +
            "FROM Transaction t JOIN t.category c " +
            "WHERE t.type = 'EXPENSE' " +
            "GROUP BY c.name")
    List<CategoryStat> getStatsByCategory();
}