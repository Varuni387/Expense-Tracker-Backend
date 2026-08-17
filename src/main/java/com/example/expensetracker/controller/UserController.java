package com.example.expensetracker.controller;

import com.example.expensetracker.model.Login;
import com.example.expensetracker.model.UserModel;
import com.example.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserModel user){
        return userService.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login){
        return userService.login(login);
    }

}
