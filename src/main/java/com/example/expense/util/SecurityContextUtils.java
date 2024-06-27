package com.example.expense.util;

import com.example.expense.entity.User;
import com.example.expense.exception.UserNotFoundException;
import com.example.expense.repository.UserRepository;
import com.example.expense.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityContextUtils {

    private static final UserRepository userRepository = ApplicationContextProvider.getApplicationContext().getBean(UserRepository.class);

    public static CustomUserDetails getCurrentUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static User getCurrentUser() {
        CustomUserDetails customUserDetails = SecurityContextUtils.getCurrentUserDetails();
        return userRepository.findByUsername(customUserDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);
    }
}
