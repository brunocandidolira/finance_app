package com.finance.spring_app.Mapper;

import com.finance.spring_app.DTO.UserDTO;
import com.finance.spring_app.domain.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserDTO dto){
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    public UserDTO map(User user){
        return new UserDTO(
                user.getEmail(),
                user.getName(),
                user.getPassword()
        );
    }
}
