package com.pfe.smartwallet.controller;

import com.pfe.smartwallet.model.Reminder;
import com.pfe.smartwallet.model.User;
import com.pfe.smartwallet.repository.ReminderRepository;
import com.pfe.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired private ReminderRepository reminderRepository;
    @Autowired private UserRepository userRepository;

    // 1. Récupérer les rappels de l'utilisateur (ID 1 pour le PFE)
    @GetMapping("/user/{userId}")
    public List<Reminder> getUserReminders(@PathVariable Long userId) {
        return reminderRepository.findByUserId(userId);
    }

    // 2. Créer un nouveau rappel
    @PostMapping("/user/{userId}")
    public Reminder createReminder(@PathVariable Long userId, @RequestBody Map<String, String> payload) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String title = payload.get("title");
        BigDecimal amount = new BigDecimal(payload.get("amount"));
        // On reçoit le nombre de secondes, on calcule la date future
        long seconds = Long.parseLong(payload.get("seconds"));
        LocalDateTime alertDate = LocalDateTime.now().plusSeconds(seconds);

        Reminder reminder = new Reminder(title, amount, alertDate, user);
        return reminderRepository.save(reminder);
    }

    // 3. Supprimer un rappel
    @DeleteMapping("/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
    }
}