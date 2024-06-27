package com.example.expense.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRegisterDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
