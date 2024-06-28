package com.example.expense.service;

import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.Expense;
import com.example.expense.entity.User;
import com.example.expense.exception.ExpenseNotFoundException;
import com.example.expense.exception.ExpenseOwnershipException;
import com.example.expense.mapper.ExpenseMapper;
import com.example.expense.repository.ExpenseRepository;
import com.example.expense.specification.ExpenseSpecification;
import com.example.expense.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public Page<ExpenseDto> getAllExpenses(String category, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        long userId = SecurityContextUtil.getCurrentUserId();
        Specification<Expense> specification = Specification.where(Specification.allOf(
                ExpenseSpecification.getSpecifications(userId, category, fromDate, toDate)));
        return expenseRepository.findAll(specification, pageable).map(expenseMapper::toDto);
    }

    public ExpenseDto getExpense(long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expense);
        return expenseMapper.toDto(expense);
    }

    public void addExpense(ExpenseDto expenseDto) {
        User user = SecurityContextUtil.getCurrentUser();
        Expense expense = expenseMapper.toEntity(expenseDto);
        expense.setUser(user);
        expenseRepository.save(expense);
    }

    public void updateExpense(long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expense);
        expenseMapper.updateEntity(expense, expenseDto);
        expenseRepository.save(expense);
    }

    public void deleteExpense(long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expense);
        expenseRepository.delete(expense);
    }

    private void checkAccess(Expense expense) {
        long userId = SecurityContextUtil.getCurrentUserId();
        if (userId != expense.getUser().getId())
            throw new ExpenseOwnershipException();
    }

    public float calculateTotalExpense(long userId, String expenseCategory, int month, int year) {
        Float totalExpense = expenseRepository.calculateTotalExpense(userId, expenseCategory, month, year);
        return totalExpense != null ? totalExpense : 0;
    }
}
