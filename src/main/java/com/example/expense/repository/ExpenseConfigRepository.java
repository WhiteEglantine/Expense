package com.example.expense.repository;

import com.example.expense.entity.ExpenseConfig;
import com.example.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseConfigRepository extends JpaRepository<ExpenseConfig, Long> {
    Optional<ExpenseConfig> findById(long id);

    List<ExpenseConfig> findAllByUserId(long userId);

    List<ExpenseConfig> findAllByActiveTrue();

}
