package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ExpenseOwnershipException extends AppRuntimeException {
    private ExpenseOwnershipException(String message) {
        super(message);
    }

    public ExpenseOwnershipException() {
        this(ExceptionMessage.EXPENSE_OWNERSHIP_VIOLATION);
    }
}
