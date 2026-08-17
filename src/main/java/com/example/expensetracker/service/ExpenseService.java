package com.example.expensetracker.service;

import com.example.expensetracker.entity.CategoryEntity;
import com.example.expensetracker.entity.ExpenseEntity;
import com.example.expensetracker.entity.UserEntity;
import com.example.expensetracker.model.ExpenseModel;
import com.example.expensetracker.model.Login;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    // Create Expense
    public ResponseEntity<Object> addExpense(ExpenseModel expenseModel) {

        Optional<UserEntity> user = userRepository.findById(expenseModel.getUserId());
        Optional<CategoryEntity> category = categoryRepository.findById(expenseModel.getCategoryId());

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (category.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        ExpenseEntity expense = new ExpenseEntity();
        expense.setUser(user.get());
        expense.setCategory(category.get());
        expense.setAmount(expenseModel.getAmount());
        expense.setExpenseDate(expenseModel.getDate());
        expense.setDescription(expenseModel.getDescription());
        expense.setPaymentMethod(expenseModel.getPaymentMethod());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepository.save(expense);

        return ResponseEntity.ok("Expense added successfully");
    }

    // Get all expenses of a user
    public ResponseEntity<List<ExpenseEntity>> getExpenses(Long userId) {
        return ResponseEntity.ok(expenseRepository.findByUserUserId(userId));
    }

    // Get by expense id of a user
    public ResponseEntity<List<ExpenseEntity>> getByExpenseId(Long userId, Long expenseId) {
        return ResponseEntity.ok(expenseRepository.findByUserUserIdAndExpenseId(userId,expenseId));
    }

    // Update Expense
    public ResponseEntity<Object> updateExpense(Long expenseId, ExpenseModel expenseModel) {

        Optional<ExpenseEntity> optionalExpense = expenseRepository.findById(expenseId);

        if (optionalExpense.isEmpty()) {
            return ResponseEntity.badRequest().body("Expense not found");
        }

        ExpenseEntity expense = optionalExpense.get();

        expense.setAmount(expenseModel.getAmount());
        expense.setExpenseDate(expenseModel.getDate());
        expense.setDescription(expenseModel.getDescription());
        expense.setPaymentMethod(expenseModel.getPaymentMethod());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepository.save(expense);

        return ResponseEntity.ok("Expense updated successfully");
    }

    // Delete Expense
    public ResponseEntity<Object> deleteExpense(Long expenseId) {

        if (!expenseRepository.existsById(expenseId)) {
            return ResponseEntity.badRequest().body("Expense not found");
        }

        expenseRepository.deleteById(expenseId);

        return ResponseEntity.ok("Expense deleted successfully");
    }
}