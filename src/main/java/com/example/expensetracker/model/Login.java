package com.example.expensetracker.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
