
package com.finance.spring_app.Service;

import com.finance.spring_app.DTO.UserDTO;
import com.finance.spring_app.Exception.EmailAlreadyExistsException;
import com.finance.spring_app.Mapper.UserMapper;
import com.finance.spring_app.Repository.UserRepository;
import com.finance.spring_app.domain.Entity.User;
import com.finance.spring_app.domain.Entity.enums.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(
            UserRepository repository,
           @Lazy PasswordEncoder passwordEncoder,
            UserMapper userMapper
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    // 🔹 Registro de usuário
    public User register(UserDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException();
        }

        User user = userMapper.map(dto);

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUserRole(Role.USER);

        return repository.save(user);
    }

    // 🔹 Método usado pelo Spring Security
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));
    }

    // 🔹 Buscar usuário por email (uso interno)
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));
    }
    //add saldo na conta
    public  User adacionarDinheiro(BigDecimal valor,User user){

        BigDecimal novoSaldo =user.getSaldo().add(valor);
        user.setSaldo(novoSaldo);
        return repository.save(user);


    }
    public User buscarPorId(String id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("cliente não encontrado!"));
    }
}
