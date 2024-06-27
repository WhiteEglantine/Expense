package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends NotFoundException {
    private RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        this(ExceptionMessage.ROLE_NOT_FOUND);
    }
}
