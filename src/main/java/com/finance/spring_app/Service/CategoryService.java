package com.finance.spring_app.Service;



import com.finance.spring_app.DTO.CategoryRequestDTO;
import com.finance.spring_app.Repository.CategoryRepository;
import com.finance.spring_app.domain.Entity.Category;
import com.finance.spring_app.domain.Entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(CategoryRequestDTO dto, User user) {
        Category category = Category.builder()
                .name(dto.getName())
                .type(dto.getType())
                .user(user)
                .build();

        return categoryRepository.save(category);
    }

    public List<Category> list(User user) {
        return categoryRepository.findByUser(user);
    }

    public void delete(String id, User user) {
        Category category = categoryRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoryRepository.delete(category);
    }
}
