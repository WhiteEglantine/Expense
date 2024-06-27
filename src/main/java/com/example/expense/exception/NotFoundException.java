package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends AppRuntimeException {

    public NotFoundException() {
        this(ExceptionMessage.INFO_NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
