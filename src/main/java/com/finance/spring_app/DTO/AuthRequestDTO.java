package com.finance.spring_app.DTO;


import lombok.Data;

@Data
public class AuthRequestDTO {

    private String email;
    private String password;
}
