package com.finance.spring_app.Controller;


import com.finance.spring_app.DTO.AuthRequestDTO;
import com.finance.spring_app.DTO.AuthResponseDTO;
import com.finance.spring_app.DTO.UserDTO;
import com.finance.spring_app.Service.AuthService;
import com.finance.spring_app.Service.UserService;
import com.finance.spring_app.domain.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO dto
    ) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
