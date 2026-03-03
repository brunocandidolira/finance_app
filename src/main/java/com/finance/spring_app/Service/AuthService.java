package com.finance.spring_app.Service;


import com.finance.spring_app.DTO.AuthRequestDTO;
import com.finance.spring_app.DTO.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {

        // 1️⃣ Autentica
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        // 2️⃣ Carrega usuário
        UserDetails userDetails =
                userService.loadUserByUsername(dto.getEmail());

        // 3️⃣ Gera token
        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }
}
