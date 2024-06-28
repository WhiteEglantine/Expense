package com.example.expense.specification;

import com.example.expense.entity.Expense;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecification {

    public static List<Specification<Expense>> getSpecifications(Long userId, String category, LocalDate fromDate, LocalDate toDate) {
        List<Specification<Expense>> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(userId)) {
            list.add(forUser(userId));
        }
        if (!ObjectUtils.isEmpty(category)) {
            list.add(inCategory(category));
        }
        if (!ObjectUtils.isEmpty(fromDate)) {
            list.add(fromDate(fromDate));
        }
        if (!ObjectUtils.isEmpty(toDate)) {
            list.add(toDate(toDate));
        }
        return list;
    }

    private static Specification<Expense> forUser(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Expense> inCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    private static Specification<Expense> fromDate(LocalDate fromDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("expenseDate"), fromDate);
    }

    private static Specification<Expense> toDate(LocalDate toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("expenseDate"), toDate);
    }
}
