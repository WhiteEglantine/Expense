package com.example.expense.entity;

import com.example.expense.constant.ExpenseCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends BaseEntity {

    @NotBlank
    @Length(max = 1000)
    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private ExpenseCategory category;

    @Column
    @NotNull
    private Float amount;

    @Column(name = "expense_date")
    @NotNull
    private LocalDate expenseDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}