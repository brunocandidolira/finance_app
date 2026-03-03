package com.finance.spring_app.Service;


import com.finance.spring_app.DTO.TransactionDTO;
import com.finance.spring_app.DTO.TransactionResponseDTO;
import com.finance.spring_app.Repository.CategoryRepository;
import com.finance.spring_app.Repository.TransactionRepository;
import com.finance.spring_app.Repository.UserRepository;
import com.finance.spring_app.domain.Entity.Category;
import com.finance.spring_app.domain.Entity.Transaction;
import com.finance.spring_app.domain.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionResponseDTO create(User user, TransactionDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = Transaction.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .type(dto.getType())
                .date(dto.getDate())
                .user(user)
                .category(category)
                .build();

        transactionRepository.save(transaction);

        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .categoryName(category.getName())
                .build();

    }


    public List<Transaction> findByUser(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    public void delete(String id) {
        transactionRepository.deleteById(id);
    }


}
