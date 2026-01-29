package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // L'héritage JpaRepository ajoute automatiquement la méthode findById
}