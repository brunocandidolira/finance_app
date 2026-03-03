package com.finance.spring_app.Controller;

import com.finance.spring_app.DTO.BalanceResponseDTO;
import com.finance.spring_app.DTO.SaldoDTO;
import com.finance.spring_app.DTO.UserDTO;

import com.finance.spring_app.DTO.UserResponseDTO;
import com.finance.spring_app.Repository.ExpenseRepository;
import com.finance.spring_app.Repository.UserRepository;
import com.finance.spring_app.Service.ExpenseService;
import com.finance.spring_app.Service.UserService;
import com.finance.spring_app.domain.Entity.Expense;
import com.finance.spring_app.domain.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;
    private  final ExpenseRepository expenseRepository;
    private final UserRepository repository;

    public UserController(UserService userService, ExpenseService expenseService, ExpenseRepository expenseRepository, UserRepository repository) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
        this.repository = repository;
    }

    // 🔹 Registro de usuário
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody UserDTO dto
    ) {
        User user = userService.register(dto);
        UserResponseDTO response = new UserResponseDTO(
                user.getId().toString(),
                user.getEmail(),
                user.getName(),
                user.getUserRole().name()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adicionarsaldo")
    public ResponseEntity<?> adicionarSaldo(
            @RequestBody SaldoDTO saldoDTO,
            Authentication authentication
    ) {
        // O 'principal' no seu caso é o objeto User que implementa UserDetails
        User user = (User) authentication.getPrincipal();

        User atualizado = userService.adacionarDinheiro(saldoDTO.getValor(), user);
        return ResponseEntity.ok("Saldo atualizado para: " + atualizado.getSaldo());
    }
    @GetMapping("/expense")
    public List<Expense> listAllExpenses() {

        return expenseRepository.findAll();
    }
    @PostMapping("/expense")

    public ResponseEntity<Expense> cadastrarDespesa(
            @RequestBody ExpenseRequestDTO data,
            Authentication authentication
    ) {
        // O 'principal' é o usuário que o seu JwtAuthenticationFilter colocou no contexto
        User userLogado = (User) authentication.getPrincipal();

        // Chamamos o seu service passando o ID do usuário logado
        Expense novaDespesa = expenseService.criarDespesa(
                userLogado.getId().toString(),
                data.amount(),
                data.description()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(novaDespesa);
    }

    // Um DTO simples para receber os dados do React
    public record ExpenseRequestDTO(BigDecimal amount, String description) {}

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new BalanceResponseDTO(user.getSaldo(), user.getName()));
    }
    }
