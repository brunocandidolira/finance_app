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


        if (user.getSaldo().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente para esta despesa");
        }


        Expense expense = Expense.builder()
                .amount(amount)
                .description(description)
                .user(user)
                .build();


        user.setSaldo(user.getSaldo().subtract(amount));
        userRepository.save(user);


        return expenseRepository.save(expense);
    }

    @Transactional // Importante para operações de escrita/deleção
    public void deletarDespesa(String idUser, String idExpense) {
        // 1. Verificamos se o usuário existe
        if (!userRepository.existsById(idUser)) {
            throw new RuntimeException("Usuário não encontrado");
        }


        Expense exp = expenseRepository.findById(idExpense)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));


        if (!exp.getUser().getId().equals(idUser)) {
            throw new RuntimeException("Você não tem permissão para deletar esta despesa");
        }


        expenseRepository.delete(exp);
    }
    @Transactional
    public Expense alterarDespesa(String idUser, String idExpense, Expense dadosAtualizados) {

        Expense exp = expenseRepository.findById(idExpense)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));


        if (!exp.getUser().getId().equals(idUser)) {
            throw new RuntimeException("Ação não permitida!");
        }


        User user = exp.getUser();


        BigDecimal saldoComExtorno = user.getSaldo().add(exp.getAmount());
        BigDecimal saldoFinal = saldoComExtorno.subtract(dadosAtualizados.getAmount());


        if (saldoFinal.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente para esta alteração.");
        }


        exp.setDescription(dadosAtualizados.getDescription());
        exp.setAmount(dadosAtualizados.getAmount());

        user.setSaldo(saldoFinal);


        return expenseRepository.save(exp);
    }
}