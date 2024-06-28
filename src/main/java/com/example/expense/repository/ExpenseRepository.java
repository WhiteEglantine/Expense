package com.example.expense.repository;

import com.example.expense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    Optional<Expense> findById(long id);

    Page<Expense> findAll(Specification<Expense> specification, Pageable pageable);

    @Query(value = "SELECT SUM(amount) FROM expense WHERE " +
            "user_id = :userId AND (:category IS NULL OR category = :category) AND " +
            "MONTH(expense_date) = :month AND YEAR(expense_date) = :year", nativeQuery = true)
    Float calculateTotalExpense(@Param("userId") long userId, @Param("category") String category,
                                @Param("month") int month, @Param("year")int year);


}
