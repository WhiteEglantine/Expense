package com.example.expense.mapper;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.constant.ExpenseInterval;
import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.entity.ExpenseConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", imports = {ExpenseCategory.class, ExpenseInterval.class})
public interface ExpenseConfigMapper {

    ExpenseConfigDto toDto(ExpenseConfigEntity expenseConfigEntity);

    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseConfigDto.getCategory()))")
    @Mapping(target = "interval", expression = "java(ExpenseInterval.valueOf(expenseConfigDto.getInterval()))")
    ExpenseConfigEntity toEntity(ExpenseConfigDto expenseConfigDto);

    List<ExpenseConfigDto> toDtoList(List<ExpenseConfigEntity> expenseConfigEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseDto.getCategory()))")
    @Mapping(target = "interval", expression = "java(ExpenseInterval.valueOf(expenseConfigDto.getInterval()))")
    void updateEntity(@MappingTarget ExpenseConfigEntity expenseConfigEntity, ExpenseConfigDto expenseConfigDto);
}
