package com.pfe.smartwallet.controller;

import com.pfe.smartwallet.model.User;
import com.pfe.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importation plus large pour PathVariable

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Suppression du caractère "往" et ajout de l'annotation correcte
    @GetMapping("/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    // Optionnel : ne pas renvoyer le mot de passe dans l'API pour la sécurité
                    user.setPasswordHash("********");
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}