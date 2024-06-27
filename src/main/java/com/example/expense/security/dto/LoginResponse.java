package com.example.expense.security.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class LoginResponse {
    private String accessToken;
    private String tokenType = "bearer";
    private Long expiresIn;
    private Set<String> privileges;

    public LoginResponse(String accessToken, Long expiresIn, Set<String> privileges) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.privileges = privileges;
    }
}

