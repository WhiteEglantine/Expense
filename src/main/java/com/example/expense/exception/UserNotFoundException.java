package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NotFoundException {
    private UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        this(ExceptionMessage.USER_NOT_FOUND);
    }
}
