package com.example.expense.exception;

import com.example.expense.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends AppRuntimeException {

    public BadRequestException() {
        this(ExceptionMessage.MALFORMED_JSON_REQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
