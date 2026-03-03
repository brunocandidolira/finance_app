package com.finance.spring_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record  UserDTO (String email, String name, String password){

}
