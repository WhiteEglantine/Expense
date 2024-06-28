package com.example.expense.service;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.Expense;
import com.example.expense.entity.User;
import com.example.expense.exception.ExpenseNotFoundException;
import com.example.expense.exception.ExpenseOwnershipException;
import com.example.expense.mapper.ExpenseMapper;
import com.example.expense.repository.ExpenseRepository;
import com.example.expense.util.SecurityContextUtil;
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
        long userId = SecurityContextUtil.getCurrentUserId();
        return expenseMapper.toDtoList(expenseRepository.findAllByUserId(userId));
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
