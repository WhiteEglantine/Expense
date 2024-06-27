package com.example.expense.service;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.Expense;
import com.example.expense.entity.User;
import com.example.expense.exception.ExpenseNotFoundException;
import com.example.expense.exception.ExpenseOwnershipException;
import com.example.expense.mapper.ExpenseMapper;
import com.example.expense.repository.ExpenseRepository;
import com.example.expense.util.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public List<ExpenseDto> getAllExpenses() {
        User user = SecurityContextUtils.getCurrentUser();
        return expenseMapper.toDtoList(expenseRepository.findAllByUser(user));
    }

    public ExpenseDto getExpense(long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expense);
        return expenseMapper.toDto(expense);
    }

    public void addExpense(ExpenseDto expenseDto) {
        User user = SecurityContextUtils.getCurrentUser();
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
        User user = SecurityContextUtils.getCurrentUser();
        if (user.getId() != expense.getUser().getId())
            throw new ExpenseOwnershipException();
    }

    public float calculateTotalExpense(long userId, ExpenseCategory expenseCategory, int month, int year) {
        return expenseRepository.calculateTotalExpense(userId, expenseCategory, month, year);
    }
}
