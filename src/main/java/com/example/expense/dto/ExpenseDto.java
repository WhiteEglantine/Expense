package com.example.expense.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseDto {

    private Long id;

    @NotBlank
    @Length(max = 1000)
    private String description;

    @NotNull
    private String category;

    @NotNull
    private Float amount;

    @NotNull
    private LocalDate expenseDate;

}