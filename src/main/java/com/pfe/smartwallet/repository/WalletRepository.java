package com.pfe.smartwallet.repository; // Vérifie que le package est bon !

import com.pfe.smartwallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUserId(Long userId);
    // C'est "extends JpaRepository" qui donne les pouvoirs magiques (save, findById...)
}