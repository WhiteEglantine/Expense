package com.example.expense.util;

import com.example.expense.entity.User;
import com.example.expense.exception.UserNotFoundException;
import com.example.expense.repository.UserRepository;
import com.example.expense.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {

    private static final UserRepository userRepository = ApplicationContextProvider.getApplicationContext().getBean(UserRepository.class);

    public static User getCurrentUser() {
        CustomUserDetails customUserDetails = SecurityContextUtil.getCurrentUserDetails();
        return userRepository.findByUsername(customUserDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);
    }

    public static long getCurrentUserId() {
        CustomUserDetails customUserDetails = SecurityContextUtil.getCurrentUserDetails();
        return customUserDetails.getId();
    }

    private static CustomUserDetails getCurrentUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
