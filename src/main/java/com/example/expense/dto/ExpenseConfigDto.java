package com.example.expense.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseConfigDto {

    private Long id;

    private Long userId;

    @NotNull
    private Float limit;

    @NotNull
    private String category;

    @NotNull
    private Boolean active;

}