package com.datasoft.service.implementation;

import com.datasoft.controller.request.LoginRequest;
import com.datasoft.controller.request.SignUpRequest;
import com.datasoft.controller.response.LoginResponse;
import com.datasoft.repository.domain.User;
import com.datasoft.configuration.security.JwtUtil;
import com.datasoft.service.AuthService;
import com.datasoft.service.UserService;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

/**
 * Implementation of AuthService interface.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final Validator validator;

    public AuthServiceImpl(UserService userService, JwtUtil jwtUtil, Validator validator) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.validator = validator;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        String token = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(token, user.getUsername(), user.getFullName());
    }

    @Override
    public LoginResponse signup(SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + signUpRequest.getUsername());
        }

        User newUser = userService.registerUser(
                signUpRequest.getFullName(),
                signUpRequest.getUsername(),
                signUpRequest.getPassword()
        );

        String token = jwtUtil.generateToken(newUser.getUsername());

        return new LoginResponse(token, newUser.getUsername(), newUser.getFullName());
    }
}

