package com.example.expense.security.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private String role;
}
