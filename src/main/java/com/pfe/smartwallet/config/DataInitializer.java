package com.pfe.smartwallet.config;

import com.pfe.smartwallet.model.Wallet;
import com.pfe.smartwallet.model.Category;
import com.pfe.smartwallet.model.User;
import com.pfe.smartwallet.repository.WalletRepository;
import com.pfe.smartwallet.repository.CategoryRepository;
import com.pfe.smartwallet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(WalletRepository walletRepo,
                                   CategoryRepository categoryRepo,
                                   UserRepository userRepo) {
        return args -> {
            // 1. Création de l'utilisateur par défaut (ID 1)
            User user = userRepo.findById(1L).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(1L);
                newUser.setFullName("Asmae Haskar");
                newUser.setEmail("asmae@example.com");
                // Note : utilise le champ exact de ton entité (password ou passwordHash)
                newUser.setPasswordHash("password123");
                return userRepo.save(newUser);
            });

            // 2. Création du Wallet (ID 1) lié à l'utilisateur
            if (walletRepo.findById(1L).isEmpty()) {
                Wallet wallet = new Wallet();
                wallet.setId(1L);
                wallet.setName("Mon Portefeuille");
                // IMPORTANT : On initialise à ZERO pour éviter les erreurs de calcul sur les revenus
                wallet.setBalance(BigDecimal.ZERO);
                wallet.setUser(user);
                walletRepo.save(wallet);
                System.out.println("✅ Wallet initialisé avec succès !");
            }

            // 3. Création des catégories indispensables
            if (categoryRepo.findById(1L).isEmpty()) {
                categoryRepo.save(new Category(1L, "Alimentation"));
            }
            if (categoryRepo.findById(2L).isEmpty()) {
                categoryRepo.save(new Category(2L, "Transport"));
            }
            // Ajoute une catégorie spécifique pour les REVENUS
            if (categoryRepo.findById(3L).isEmpty()) {
                categoryRepo.save(new Category(3L, "Salaire / Revenu"));
                System.out.println("✅ Catégories initialisées !");
            }
        };
    }
}