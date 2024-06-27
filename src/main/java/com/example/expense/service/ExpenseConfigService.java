package com.example.expense.service;

import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.entity.ExpenseConfigEntity;
import com.example.expense.entity.User;
import com.example.expense.exception.ExpenseConfigNotFoundException;
import com.example.expense.exception.ExpenseOwnershipException;
import com.example.expense.mapper.ExpenseConfigMapper;
import com.example.expense.repository.ExpenseConfigRepository;
import com.example.expense.util.SecurityContextUtils;
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
        User user = SecurityContextUtils.getCurrentUser();
        return expenseConfigMapper.toDtoList(expenseConfigRepository.findAllByUserId(user.getId()));
    }

    public ExpenseConfigDto getExpenseConfig(long id) {
        ExpenseConfigEntity expenseConfigEntity = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfigEntity);
        return expenseConfigMapper.toDto(expenseConfigEntity);
    }

    public void addExpenseConfig(ExpenseConfigDto expenseConfigDto) {
        User user = SecurityContextUtils.getCurrentUser();
        ExpenseConfigEntity expenseConfigEntity = expenseConfigMapper.toEntity(expenseConfigDto);
        expenseConfigEntity.setUser(user);
        expenseConfigRepository.save(expenseConfigEntity);
    }

    public void updateExpenseConfig(long id, ExpenseConfigDto expenseConfigDto) {
        ExpenseConfigEntity expenseConfigEntity = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfigEntity);
        expenseConfigMapper.updateEntity(expenseConfigEntity, expenseConfigDto);
        expenseConfigRepository.save(expenseConfigEntity);
    }

    public void deleteExpenseConfig(long id) {
        ExpenseConfigEntity expenseConfigEntity = expenseConfigRepository.findById(id).orElseThrow(ExpenseConfigNotFoundException::new);
        checkAccess(expenseConfigEntity);
        expenseConfigRepository.delete(expenseConfigEntity);
    }

    private void checkAccess(ExpenseConfigEntity expenseConfigEntity) {
        User user = SecurityContextUtils.getCurrentUser();
        if (user.getId() != expenseConfigEntity.getUser().getId())
            throw new ExpenseOwnershipException();
    }
}
