package com.example.expense.entity;

import com.example.expense.constant.ExpenseCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "expense_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseConfig extends BaseEntity {

    @Column
    @NotNull
    private Float limit;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private ExpenseCategory category;

    @Column
    @NotNull
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}