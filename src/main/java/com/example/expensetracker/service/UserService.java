package com.example.expensetracker.service;

import com.example.expensetracker.entity.UserEntity;
import com.example.expensetracker.model.Login;
import com.example.expensetracker.model.UserModel;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.config.SecurityConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> signup(UserModel request){
            UserEntity user=new UserEntity();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setHashedPassword(passwordEncoder.encode(request.getHashedPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<Object> login(Login login){
        UserEntity user=userRepository.findByUsername(login.getUsername());
        if(user != null && passwordEncoder.matches(user.getHashedPassword(), login.getPassword())){
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.ok("Username or password incorrect");
    }
}
