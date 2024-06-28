package com.example.expense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Column(name = "generation_date")
    @NotNull
    private LocalDate generationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_config_id", nullable = false)
    private ExpenseConfig expenseConfig;

    @Column(name = "read", columnDefinition = "BIT DEFAULT FALSE")
    @NotNull
    private boolean read;
}