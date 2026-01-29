package com.pfe.smartwallet.service;

import com.pfe.smartwallet.model.Wallet;
import com.pfe.smartwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public List<Wallet> getMyWallets(Long userId) {
        return walletRepository.findByUserId(userId);
    }
}