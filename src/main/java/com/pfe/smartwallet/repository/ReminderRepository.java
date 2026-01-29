package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    // Trouver tous les rappels d'un utilisateur spécifique
    List<Reminder> findByUserId(Long userId);
}