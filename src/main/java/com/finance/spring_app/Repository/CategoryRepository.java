package com.finance.spring_app.Repository;



import com.finance.spring_app.domain.Entity.Category;
import com.finance.spring_app.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findByUser(User user);

    Optional<Category> findByIdAndUser(String id, User user);
}
