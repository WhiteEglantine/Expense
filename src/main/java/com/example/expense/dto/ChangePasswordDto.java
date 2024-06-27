package com.example.expense.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    private String newPassword;
}
