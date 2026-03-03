package com.finance.spring_app.Controller;


import com.finance.spring_app.DTO.TransactionDTO;
import com.finance.spring_app.DTO.TransactionResponseDTO;
import com.finance.spring_app.Service.TransactionService;
import com.finance.spring_app.domain.Entity.Transaction;
import com.finance.spring_app.domain.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionDTO dto) {
        // Simulando um usuário vindo da autenticação (isso seria substituído pelo Spring Security)
        User user = new User();

        TransactionResponseDTO response = transactionService.create(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(transactionService.findByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}