package com.example.expensetracker.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String username;
    private String hashedPassword;
    private String phoneNumber;
    private String email;
    LocalDateTime createdAt;
}
