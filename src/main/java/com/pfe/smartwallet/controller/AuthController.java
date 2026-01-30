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
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    // Cette méthode permet de CRÉER ton propre compte
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Erreur : cet email est déjà utilisé.");
        }
        
        // Sauvegarde de l'utilisateur
        User savedUser = userRepository.save(user);

        // Création d'un portefeuille automatique pour le nouvel utilisateur
        Wallet wallet = new Wallet();
        wallet.setName("Portefeuille Principal");
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setUser(savedUser);
        walletRepository.save(wallet);

        return ResponseEntity.ok(savedUser);
    }

    // Cette méthode permet de se CONNECTER
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Comparaison des mots de passe
            if (user.getPasswordHash().equals(loginRequest.getPasswordHash())) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect.");
    }
}
