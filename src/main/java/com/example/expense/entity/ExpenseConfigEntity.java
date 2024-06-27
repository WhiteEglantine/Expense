package com.example.expense.entity;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.constant.ExpenseInterval;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "expense_config")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseConfigEntity extends BaseEntity {

    @Column
    @NotNull
    private Float limit;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private ExpenseCategory category;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private ExpenseInterval interval;

    @Column
    @NotNull
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}