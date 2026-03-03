package com.finance.spring_app.Repository;

import com.finance.spring_app.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<User,String> {
    boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);
}
