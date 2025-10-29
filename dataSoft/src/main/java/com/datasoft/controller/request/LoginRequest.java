package com.datasoft.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "The username is required")
    @Size(max = 20, message = "The username cannot exceed 20 characters")
    private String username;

    @NotBlank(message = "The password is required")
    @Size(max = 20, message = "The password cannot exceed 20 characters")
    private String password;
}
