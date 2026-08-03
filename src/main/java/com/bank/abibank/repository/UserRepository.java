package com.bank.abibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.abibank.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);
}