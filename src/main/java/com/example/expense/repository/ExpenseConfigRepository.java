package com.example.expense.repository;

import com.example.expense.entity.ExpenseConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseConfigRepository extends JpaRepository<ExpenseConfigEntity, Long> {
    Optional<ExpenseConfigEntity> findById(long id);

    List<ExpenseConfigEntity> findAllByUserId(long userId);
}
