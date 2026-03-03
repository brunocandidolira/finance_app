package com.finance.spring_app.DTO;

import java.math.BigDecimal;

public record BalanceResponseDTO(BigDecimal saldo, String name) {
}
