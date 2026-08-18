package com.example.expensetracker.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseModel {
    private Long userId;
    private BigDecimal amount;
    private Long categoryId;
    private LocalDate date;
    private String description;
    private String paymentMethod;
}
