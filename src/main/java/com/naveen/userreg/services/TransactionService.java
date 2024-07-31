package com.naveen.userreg.services;

import com.naveen.userreg.models.Transaction;
import com.naveen.userreg.repos.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepo.findById(id);
    }
}
