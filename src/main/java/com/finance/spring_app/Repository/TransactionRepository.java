package com.finance.spring_app.Repository;


import com.finance.spring_app.domain.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findByUserId(String userId);
}
