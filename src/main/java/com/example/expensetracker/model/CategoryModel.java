package com.example.expensetracker.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

    private Long userId;
    private String categoryName;
    private LocalDateTime createdAt;
}