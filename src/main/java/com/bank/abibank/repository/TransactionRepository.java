package com.bank.abibank.repository;

import com.bank.abibank.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository
        extends JpaRepository<Transaction, Integer> {

    List<Transaction> findBySenderIdOrReceiverId(
            String senderId,
            String receiverId
    );
}