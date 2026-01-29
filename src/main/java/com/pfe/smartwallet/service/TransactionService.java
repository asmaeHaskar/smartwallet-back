package com.pfe.smartwallet.service;

import com.pfe.smartwallet.dto.TransactionRequestDTO;
import com.pfe.smartwallet.model.Category;
import com.pfe.smartwallet.model.Transaction;
import com.pfe.smartwallet.model.Wallet;
import com.pfe.smartwallet.repository.CategoryRepository;
import com.pfe.smartwallet.repository.TransactionRepository;
import com.pfe.smartwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional // Sécurité : Tout s'annule si une erreur survient
    public Transaction addTransaction(TransactionRequestDTO request) {

        // 1. Récupérer le Wallet (Portefeuille) via son ID
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet introuvable avec l'ID : " + request.getWalletId()));

        // 2. Récupérer la Catégorie via son ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'ID : " + request.getCategoryId()));

        // 3. Créer l'objet Transaction
        Transaction txn = new Transaction();
        txn.setAmount(request.getAmount());
        txn.setNote(request.getNote());
        txn.setDate(LocalDateTime.now()); // Date actuelle
        txn.setWallet(wallet);
        txn.setCategory(category);
        // Si tu as un champ imageUrlOcr dans ta table transaction, tu peux l'ajouter ici :
        // txn.setImageUrlOcr(request.getImageUrl());

        // 4. MISE À JOUR DU SOLDE (Logique Métier)
        // On compare le type (Dépense ou Revenu)
        if ("EXPENSE".equalsIgnoreCase(request.getType())) {
            // C'est une dépense : on soustrait
            wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
        } else {
            // C'est un revenu : on ajoute
            wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        }

        // Sauvegarder le nouveau solde du portefeuille
        walletRepository.save(wallet);

        // 5. Sauvegarder la transaction en base de données
        return transactionRepository.save(txn);
    }
}