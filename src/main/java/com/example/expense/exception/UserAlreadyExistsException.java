package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends BadRequestException {
    private UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException() {
        this(ExceptionMessage.USER_ALREADY_EXISTS);
    }
}
