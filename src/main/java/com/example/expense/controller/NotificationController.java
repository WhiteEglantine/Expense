package com.example.expense.controller;

import com.example.expense.dto.NotificationDto;
import com.example.expense.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/notifications")
@SecurityRequirement(name = "Client APIs")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllUnread() {
        return ResponseEntity.ok().body(notificationService.getAllUnreadNotifications());
    }
}

