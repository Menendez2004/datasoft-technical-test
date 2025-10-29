package com.datasoft.service;

import com.datasoft.controller.response.UserResponse;
import com.datasoft.repository.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> authenticateUser(String username, String password);

    User registerUser(String fullName, String username, String password);

    boolean existsByUsername(String username);

    UserResponse toUserResponse(User user);
}
