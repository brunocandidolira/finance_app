package com.finance.spring_app.Repository;

import com.finance.spring_app.domain.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,String> {

    List<Expense> findByUserId(String userId);
}
