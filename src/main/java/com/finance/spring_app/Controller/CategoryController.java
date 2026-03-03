package com.finance.spring_app.Controller;


import com.finance.spring_app.DTO.CategoryRequestDTO;
import com.finance.spring_app.Service.CategoryService;
import com.finance.spring_app.domain.Entity.Category;
import com.finance.spring_app.domain.Entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(
            @RequestBody CategoryRequestDTO dto,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(categoryService.create(dto, user));
    }

    @GetMapping
    public ResponseEntity<List<Category>> list(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(categoryService.list(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @AuthenticationPrincipal User user
    ) {
        categoryService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}

