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
public class SignUpRequest {

    @NotBlank(message = "The full name is required")
    @Size(max = 30, message = "The full name cannot exceed 30 characters")
    private String fullName;

    @NotBlank(message = "The username is required")
    @Size(max = 20, message = "The username cannot exceed 20 characters")
    private String username;

    @NotBlank(message = "The password is required")
    @Size(min = 6, max = 20, message = "The password must be between 6 and 20 characters")
    private String password;
}
