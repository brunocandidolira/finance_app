package com.finance.spring_app.Service;

import com.finance.spring_app.Repository.ExpenseRepository;
import com.finance.spring_app.Repository.UserRepository;
import com.finance.spring_app.domain.Entity.Expense;
import com.finance.spring_app.domain.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository; // Use o repository diretamente para salvar o user

    @Transactional // Garante que a despesa e o saldo sejam salvos juntos
    public Expense criarDespesa(String userId, BigDecimal amount, String description) {

        // 1. Busca o usuário (Lança exceção se não existir)
        User user = userRepository.findById(String.valueOf(UUID.fromString(userId)))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Verifica se tem saldo (Opcional, mas recomendado)
        if (user.getSaldo().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente para esta despesa");
        }

        // 3. Cria a despesa
        Expense expense = Expense.builder()
                .amount(amount)
                .description(description)
                .user(user)
                .build();

        // 4. Atualiza o saldo do usuário
        user.setSaldo(user.getSaldo().subtract(amount));
        userRepository.save(user);

        // 5. Salva e retorna a despesa
        return expenseRepository.save(expense);
    }
}