package com.example.expensetracker.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseModel {
    private Long userId;
    private  Double amount;
    private Long categoryId;
    private LocalDate date;
    private String description;
    private String paymentMethod;
}
