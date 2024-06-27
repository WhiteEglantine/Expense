package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExpenseConfigNotFoundException extends NotFoundException {
    private ExpenseConfigNotFoundException(String message) {
        super(message);
    }

    public ExpenseConfigNotFoundException() {
        this(ExceptionMessage.EXPENSE_CONFIG_NOT_FOUND);
    }
}
