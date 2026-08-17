package com.example.expensetracker.service;

import com.example.expensetracker.entity.CategoryEntity;
import com.example.expensetracker.entity.UserEntity;
import com.example.expensetracker.model.CategoryModel;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // Add Category
    public ResponseEntity<Object> addCategory(CategoryModel categoryModel) {

        Optional<UserEntity> user = userRepository.findById(categoryModel.getUserId());

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (categoryRepository.existsByUserUserIdAndCategoryName(
                categoryModel.getUserId(),
                categoryModel.getCategoryName())) {

            return ResponseEntity.badRequest().body("Category already exists");
        }

        CategoryEntity category = new CategoryEntity();

        category.setCategoryName(categoryModel.getCategoryName());
        category.setUser(user.get());
        category.setCreatedAt(LocalDateTime.now());

        categoryRepository.save(category);

        return ResponseEntity.ok("Category added successfully");
    }

    // Get Categories
    public ResponseEntity<List<CategoryEntity>> getCategories(Long userId) {
        return ResponseEntity.ok(categoryRepository.findByUserUserId(userId));
    }

    // Update Category
    public ResponseEntity<Object> updateCategory(Long userId, Long categoryId, CategoryModel categoryModel) {

        Optional<CategoryEntity> optionalCategory = categoryRepository.findByCategoryIdAndUserUserId(categoryId, userId);

        if(optionalCategory.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found for this user");
        }

        CategoryEntity category = optionalCategory.get();
        category.setCategoryName(categoryModel.getCategoryName());
        categoryRepository.save(category);
        return ResponseEntity.ok("Category updated successfully");
    }

    // Delete Category
    public ResponseEntity<Object> deleteCategory(Long categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        categoryRepository.deleteById(categoryId);

        return ResponseEntity.ok("Category deleted successfully");
    }
}