package com.example.expense.service;

import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.entity.ExpenseConfig;
import com.example.expense.entity.User;
import com.example.expense.exception.ExpenseConfigNotFoundException;
import com.example.expense.exception.ExpenseOwnershipException;
import com.example.expense.mapper.ExpenseConfigMapper;
import com.example.expense.repository.ExpenseConfigRepository;
import com.example.expense.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseConfigService {

    private final ExpenseConfigRepository expenseConfigRepository;
    private final ExpenseConfigMapper expenseConfigMapper;

    public List<ExpenseConfigDto> getAllExpenseConfigs() {
        long userId = SecurityContextUtil.getCurrentUserId();
        return expenseConfigMapper.toDtoList(expenseConfigRepository.findAllByUserId(userId));
    }

    public ExpenseConfigDto getExpenseConfig(long id) {
        ExpenseConfig expenseConfig = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfig);
        return expenseConfigMapper.toDto(expenseConfig);
    }

    public void addExpenseConfig(ExpenseConfigDto expenseConfigDto) {
        User user = SecurityContextUtil.getCurrentUser();
        ExpenseConfig expenseConfig = expenseConfigMapper.toEntity(expenseConfigDto);
        expenseConfig.setUser(user);
        expenseConfigRepository.save(expenseConfig);
    }

    public void updateExpenseConfig(long id, ExpenseConfigDto expenseConfigDto) {
        ExpenseConfig expenseConfig = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfig);
        expenseConfigMapper.updateEntity(expenseConfig, expenseConfigDto);
        expenseConfigRepository.save(expenseConfig);
    }

    public void deleteExpenseConfig(long id) {
        ExpenseConfig expenseConfig = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfig);
        expenseConfigRepository.delete(expenseConfig);
    }

    private void checkAccess(ExpenseConfig expenseConfig) {
        long userId = SecurityContextUtil.getCurrentUserId();
        if (userId != expenseConfig.getUser().getId())
            throw new ExpenseOwnershipException();
    }

    public List<ExpenseConfigDto> getAllActiveExpenseConfigs() {
        return expenseConfigMapper.toDtoList(expenseConfigRepository.findAllByActiveTrue());
    }

    public ExpenseConfig getExpenseConfigEntity(long id) {
        return expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
    }
}
