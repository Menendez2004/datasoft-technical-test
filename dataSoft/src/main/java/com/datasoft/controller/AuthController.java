package com.datasoft.controller;
import com.datasoft.controller.request.LoginRequest;
import com.datasoft.controller.request.SignUpRequest;
import com.datasoft.controller.response.LoginResponse;
import com.datasoft.service.AuthService;
import jakarta.validation.Valid;
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
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public LoginResponse signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signup(signUpRequest);
    }
}
