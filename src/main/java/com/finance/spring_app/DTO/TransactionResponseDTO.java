package com.finance.spring_app.DTO;


import com.finance.spring_app.domain.Entity.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionResponseDTO {

    private String id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDate date;
    private String categoryName;
}
