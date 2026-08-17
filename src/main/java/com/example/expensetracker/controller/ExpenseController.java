package com.example.expensetracker.controller;

import com.example.expensetracker.entity.ExpenseEntity;
import com.example.expensetracker.model.ExpenseModel;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addExpense(@RequestBody ExpenseModel expense) {
        return expenseService.addExpense(expense);
    }
    @GetMapping("/{userId}/{expenseId}")
    public ResponseEntity<List<ExpenseEntity>> getByExpenseId(@PathVariable Long userId,@PathVariable Long expenseId) {
        return expenseService.getByExpenseId(userId,expenseId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseEntity>> getExpenses(@PathVariable Long userId) {
        return expenseService.getExpenses(userId);
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long expenseId,
                                                @RequestBody ExpenseModel expense) {
        return expenseService.updateExpense(expenseId, expense);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long expenseId) {
        return expenseService.deleteExpense(expenseId);
    }
}
