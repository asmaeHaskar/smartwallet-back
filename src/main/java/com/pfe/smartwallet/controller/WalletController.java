package com.pfe.smartwallet.controller;

import com.pfe.smartwallet.dto.CategoryStat;
import com.pfe.smartwallet.model.Category;
import com.pfe.smartwallet.model.Transaction;
import com.pfe.smartwallet.model.Wallet;
import com.pfe.smartwallet.repository.CategoryRepository;
import com.pfe.smartwallet.repository.TransactionRepository;
import com.pfe.smartwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired private WalletRepository walletRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private CategoryRepository categoryRepository;

    // Récupérer le solde et les transactions
    @GetMapping("/wallets/{id}")
    public Wallet getWallet(@PathVariable Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet non trouvé"));
    }

    // Ajouter une transaction standard
    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        Wallet wallet = walletRepository.findById(transaction.getWallet().getId())
                .orElseThrow(() -> new RuntimeException("Wallet introuvable"));

        if ("EXPENSE".equals(transaction.getType())) {
            wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        } else {
            wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        }

        transaction.setWallet(wallet);
        walletRepository.save(wallet);
        return transactionRepository.save(transaction);
    }

    // Stats pour les graphiques
    @GetMapping("/stats")
    public List<CategoryStat> getStats() {
        return transactionRepository.getStatsByCategory();
    }

    // --- FONCTIONNALITÉ 1 : RECHARGER (RECEIVE) ---
    @PostMapping("/wallets/{id}/topup")
    public Wallet topup(@PathVariable Long id, @RequestBody Map<String, BigDecimal> payload) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet introuvable"));
        BigDecimal amount = payload.get("amount");

        wallet.setBalance(wallet.getBalance().add(amount)); // On ajoute l'argent
        walletRepository.save(wallet);

        // On crée une trace dans l'historique (Catégorie 8 = Autre)
        Category catAutre = categoryRepository.findById(8L).orElse(null);
        Transaction t = new Transaction(amount, "Rechargement CB", wallet, catAutre, "INCOME");
        transactionRepository.save(t);

        return wallet;
    }

    // --- FONCTIONNALITÉ 2 : VIREMENT (SEND) ---
    @PostMapping("/wallets/{id}/transfer")
    public Wallet transfer(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet introuvable"));
        BigDecimal amount = new BigDecimal(payload.get("amount").toString());
        String receiver = payload.get("receiver").toString();

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Solde insuffisant");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount)); // On retire l'argent
        walletRepository.save(wallet);

        // On crée une trace dans l'historique
        Category catAutre = categoryRepository.findById(8L).orElse(null);
        Transaction t = new Transaction(amount, "Virement vers " + receiver, wallet, catAutre, "EXPENSE");
        transactionRepository.save(t);

        return wallet;
    }
}