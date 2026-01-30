package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importation cruciale ici
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Cette méthode doit retourner un Optional<User> pour que le controlleur fonctionne
    Optional<User> findByEmail(String email);

    // Utile pour la vérification lors de l'inscription
    boolean existsByEmail(String email);
}