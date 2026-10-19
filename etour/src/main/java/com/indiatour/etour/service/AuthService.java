package com.indiatour.etour.service;

import com.indiatour.etour.dto.request.LoginRequest;
import com.indiatour.etour.dto.request.SignupRequest;
import com.indiatour.etour.dto.response.JwtResponse;
import com.indiatour.etour.dto.response.MessageResponse;

public interface AuthService {

    MessageResponse registerUser(SignupRequest signupRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);
}