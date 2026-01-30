package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Indispensable pour le login
    Optional<User> findByEmail(String email);

    // Indispensable pour le register
    boolean existsByEmail(String email);
}