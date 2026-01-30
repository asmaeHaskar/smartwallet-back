package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByCategoryIdAndUserId(Long categoryId, Long userId);
}