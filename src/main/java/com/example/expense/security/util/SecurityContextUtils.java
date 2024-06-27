package com.example.expense.security.util;

import com.example.expense.security.jwt.CustomUserDetails;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class SecurityContextUtils {

    public static CustomUserDetails getCurrentUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
