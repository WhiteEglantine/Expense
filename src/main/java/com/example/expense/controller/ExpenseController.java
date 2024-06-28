package com.example.expense.controller;

import com.example.expense.dto.ExpenseDto;
import com.example.expense.dto.TotalExpenseDto;
import com.example.expense.service.ExpenseService;
import com.example.expense.util.SecurityContextUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
@SecurityRequirement(name = "Authenticated APIs")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<Page<ExpenseDto>> getAll(@RequestParam(required = false) String category,
                                                   @RequestParam(required = false) LocalDate fromDate,
                                                   @RequestParam(required = false) LocalDate toDate,
                                                   Pageable pageable) {
        Page<ExpenseDto> result = expenseService.getAllExpenses(category, fromDate, toDate, pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> get(@PathVariable long id) {
        return ResponseEntity.ok().body(expenseService.getExpense(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> add(@Valid @RequestBody ExpenseDto expenseDto) {
        expenseService.addExpense(expenseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody ExpenseDto expenseDto) {
        expenseService.updateExpense(id, expenseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total")
    public ResponseEntity<TotalExpenseDto> getTotalExpense(@RequestParam(name = "category", required = false) String category) {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        return ResponseEntity.ok().body(TotalExpenseDto.builder()
                .totalAmount(expenseService.calculateTotalExpense(
                        SecurityContextUtil.getCurrentUserId(), category, currentMonth, currentYear))
                .build());
    }
}

