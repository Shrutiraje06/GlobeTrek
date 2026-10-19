package com.indiatour.etour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.dto.request.LoginRequest;
import com.indiatour.etour.dto.request.SignupRequest;
import com.indiatour.etour.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody SignupRequest signupRequest) {

        return ResponseEntity.ok(
                authService.registerUser(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( 
            @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(
                authService.authenticateUser(loginRequest));
    }
}