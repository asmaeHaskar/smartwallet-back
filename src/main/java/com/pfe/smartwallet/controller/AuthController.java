package com.pfe.smartwallet.controller;

import com.pfe.smartwallet.model.User;
import com.pfe.smartwallet.model.Wallet;
import com.pfe.smartwallet.repository.UserRepository;
import com.pfe.smartwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional; // Importation forcée pour corriger l'erreur Locale

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setName("Mon Portefeuille");
        wallet.setBalance(new BigDecimal("100.00")); // Solde initial de bienvenue
        wallet.setUser(savedUser);
        walletRepository.save(wallet);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // Utilisation de Optional pour récupérer l'utilisateur
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Comparaison avec passwordHash (vérifie que c'est bien écrit dans User.java)
            if (user.getPasswordHash() != null && user.getPasswordHash().equals(loginRequest.getPasswordHash())) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Information incorrecte");
    }
}