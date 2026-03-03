package com.finance.spring_app.DTO;

import com.finance.spring_app.domain.Entity.enums.CategoryType;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private CategoryType type;
}
