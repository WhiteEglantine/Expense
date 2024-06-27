package com.example.expense.controller;

import com.example.expense.dto.ChangePasswordDto;
import com.example.expense.dto.UpdateUserDto;
import com.example.expense.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@SecurityRequirement(name = "Client APIs")
public class UserController {

    private final UserService userService;

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserByAdmin(updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
        return ResponseEntity.ok().build();
    }
}

