package com.example.expense.mapper;

import com.example.expense.constant.ExpenseCategory;
import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.entity.ExpenseConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", imports = {ExpenseCategory.class})
public interface ExpenseConfigMapper {

    @Mapping(target = "userId", source = "expenseConfig.user.id")
    ExpenseConfigDto toDto(ExpenseConfig expenseConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseConfigDto.getCategory()))")
    ExpenseConfig toEntity(ExpenseConfigDto expenseConfigDto);

    List<ExpenseConfigDto> toDtoList(List<ExpenseConfig> expenseConfigEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", expression = "java(ExpenseCategory.valueOf(expenseConfigDto.getCategory()))")
    void updateEntity(@MappingTarget ExpenseConfig expenseConfig, ExpenseConfigDto expenseConfigDto);
}
