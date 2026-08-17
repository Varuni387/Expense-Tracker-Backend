package com.example.expensetracker.controller;

import com.example.expensetracker.entity.CategoryEntity;
import com.example.expensetracker.model.CategoryModel;
import com.example.expensetracker.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryModel category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryEntity>> getCategories(@PathVariable Long userId) {
        return categoryService.getCategories(userId);
    }

    @PutMapping("/user/{userId}/update/{categoryId}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @RequestBody CategoryModel category) {

        return categoryService.updateCategory(userId, categoryId, category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
