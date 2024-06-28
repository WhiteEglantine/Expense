package com.example.expense.controller;

import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.service.ExpenseConfigService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/expense-configs")
@SecurityRequirement(name = "Authenticated APIs")
public class ExpenseConfigController {

    private final ExpenseConfigService expenseConfigService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(expenseConfigService.getAllExpenseConfigs());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.ok().body(expenseConfigService.getExpenseConfig(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> add(@Valid @RequestBody ExpenseConfigDto expenseConfigDto) {
        expenseConfigService.addExpenseConfig(expenseConfigDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody ExpenseConfigDto expenseConfigDto) {
        expenseConfigService.updateExpenseConfig(id, expenseConfigDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        expenseConfigService.deleteExpenseConfig(id);
        return ResponseEntity.ok().build();
    }
}

