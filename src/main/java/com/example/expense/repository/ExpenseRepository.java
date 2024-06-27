package com.example.expense.repository;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.entity.Expense;
import com.example.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findById(long id);

    List<Expense> findAllByUser(User user);

    @Query(value = "SELECT SUM(amount) FROM expense WHERE " +
            "user_id = ?1 AND category = ?2 AND MONTH(expense_date) = ?3 AND YEAR(expense_date) = ?4", nativeQuery = true)
    float calculateTotalExpense(long userId, ExpenseCategory category, int month, int year);


}
