package com.example.expense.security.entity;

import com.example.expense.converter.JsonSetConverter;
import com.example.expense.entity.BaseEntity;
import com.example.expense.security.constant.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    @Column(columnDefinition = "json", nullable = false)
    @Convert(converter = JsonSetConverter.class)
    private Set<String> privileges;
}
