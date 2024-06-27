package com.example.expense.service;

import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.ExpenseEntity;
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
        return expenseMapper.toDtoList(expenseRepository.findAllByUserId(user.getId()));
    }

    public ExpenseDto getExpense(long id) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expenseEntity);
        return expenseMapper.toDto(expenseEntity);
    }

    public void addExpense(ExpenseDto expenseDto) {
        User user = SecurityContextUtils.getCurrentUser();
        ExpenseEntity expenseEntity = expenseMapper.toEntity(expenseDto);
        expenseEntity.setUser(user);
        expenseRepository.save(expenseEntity);
    }

    public void updateExpense(long id, ExpenseDto expenseDto) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expenseEntity);
        expenseMapper.updateEntity(expenseEntity, expenseDto);
        expenseRepository.save(expenseEntity);
    }

    public void deleteExpense(long id) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        checkAccess(expenseEntity);
        expenseRepository.delete(expenseEntity);
    }

    private void checkAccess(ExpenseEntity expenseEntity) {
        User user = SecurityContextUtils.getCurrentUser();
        if (user.getId() != expenseEntity.getUser().getId())
            throw new ExpenseOwnershipException();
    }
}
