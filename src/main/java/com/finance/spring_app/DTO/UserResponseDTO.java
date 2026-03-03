package com.finance.spring_app.DTO;

import java.math.BigDecimal;

public record UserResponseDTO(
        String id,
        String name,
        String email,
        String role
) {
}