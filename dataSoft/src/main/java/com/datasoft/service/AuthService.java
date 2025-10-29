package com.datasoft.service;

import com.datasoft.controller.request.LoginRequest;
import com.datasoft.controller.request.SignUpRequest;
import com.datasoft.controller.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse signup(SignUpRequest signUpRequest);
}
