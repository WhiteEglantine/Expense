package com.example.expense.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseConfigDto {

    @NotNull
    private Float limit;

    @NotNull
    private String category;

    @NotNull
    private String interval;

    @NotNull
    private Boolean active;

}