package com.finance.spring_app.DTO;



import com.finance.spring_app.domain.Entity.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {

    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDate date;
    private String categoryId;
}
