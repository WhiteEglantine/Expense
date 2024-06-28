package com.example.expense.repository;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.entity.Expense;
import com.example.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findById(long id);

    List<Expense> findAllByUserId(long userId);

    @Query(value = "SELECT SUM(amount) FROM expense WHERE " +
            "user_id = :userId AND (:category IS NULL OR category = :category) AND " +
            "MONTH(expense_date) = :month AND YEAR(expense_date) = :year", nativeQuery = true)
    Float calculateTotalExpense(@Param("userId") long userId, @Param("category") String category,
                                @Param("month") int month, @Param("year")int year);


}
