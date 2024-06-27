package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends NotFoundException {
    private ExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseNotFoundException() {
        this(ExceptionMessage.EXPENSE_NOT_FOUND);
    }
}
