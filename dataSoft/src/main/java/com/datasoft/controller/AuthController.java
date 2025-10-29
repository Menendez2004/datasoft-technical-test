package com.datasoft.controller;

import com.datasoft.configuration.web.ApiResponse;
import com.datasoft.controller.request.LoginRequest;
import com.datasoft.controller.request.SignUpRequest;
import com.datasoft.controller.response.LoginResponse;
import com.datasoft.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.dataOnly(response));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<LoginResponse>> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        LoginResponse response = authService.signup(signUpRequest);
        return ResponseEntity.ok(ApiResponse.dataOnly(response));
    }
}
