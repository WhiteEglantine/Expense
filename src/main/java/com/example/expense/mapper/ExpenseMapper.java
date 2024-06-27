package com.example.expense.mapper;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.dto.ExpenseDto;
import com.example.expense.entity.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", imports = ExpenseCategory.class)
public interface ExpenseMapper {

    ExpenseDto toDto(ExpenseEntity expenseEntity);

    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseDto.getCategory()))")
    ExpenseEntity toEntity(ExpenseDto expenseDto);

    List<ExpenseDto> toDtoList(List<ExpenseEntity> expenseEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseDto.getCategory()))")
    void updateEntity(@MappingTarget ExpenseEntity expenseEntity, ExpenseDto expenseDto);
}
