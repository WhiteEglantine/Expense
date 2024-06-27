package com.example.expense.mapper;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", imports = ExpenseCategory.class)
public interface ExpenseMapper {

    ExpenseDto toDto(Expense expense);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseDto.getCategory()))")
    Expense toEntity(ExpenseDto expenseDto);

    List<ExpenseDto> toDtoList(List<Expense> expenseEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseDto.getCategory()))")
    void updateEntity(@MappingTarget Expense expense, ExpenseDto expenseDto);
}
