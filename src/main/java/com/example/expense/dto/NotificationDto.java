package com.example.expense.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotificationDto {

    private LocalDate generationDate;

    private String category;

    private boolean read;
}