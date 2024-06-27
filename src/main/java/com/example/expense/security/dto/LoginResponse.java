package com.example.expense.security.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String accessToken;
    private String tokenType = "bearer";
    private Long expiresIn;
    private String roles;

    public LoginResponse(String accessToken, Long expiresIn, String roles) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.roles = roles;
    }
}

