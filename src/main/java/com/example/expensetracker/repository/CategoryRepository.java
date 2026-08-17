package com.example.expensetracker.repository;

import com.example.expensetracker.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByUserUserId(Long userId);

    boolean existsByUserUserIdAndCategoryName(Long userId, String categoryName);

    Optional<CategoryEntity> findByCategoryIdAndUserUserId(Long categoryId, Long userId);
}