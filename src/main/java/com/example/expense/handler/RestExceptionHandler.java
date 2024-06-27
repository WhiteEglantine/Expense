package com.example.expense.handler;

import com.example.expense.constant.ExceptionMessage;
import com.example.expense.dto.MessageResponse;
import com.example.expense.exception.AppRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse(ExceptionMessage.MALFORMED_JSON_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus httpStatus = getHttpStatus(statusCode);
        return ResponseEntity.badRequest().body(new MessageResponse(httpStatus.getReasonPhrase()));
    }

    @ExceptionHandler(AppRuntimeException.class)
    public ResponseEntity<Object> handleAppExceptions(AppRuntimeException exception) {
        log.error("App specific exception occurred.", exception);
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);

        return ResponseEntity.status(responseStatus.code())
                .body(new MessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException exception) {
        log.error("Authentication exception occurred.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse(ExceptionMessage.INVALID_DATA));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        log.error("Authorization exception occurred.", exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MessageResponse(ExceptionMessage.ACCESS_DENIED));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception exception) {
        log.error("General exception occurred.", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(ExceptionMessage.INTERNAL_ERROR));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDatabaseExceptions(DataIntegrityViolationException exception) {
        log.error("Database exception occurred.", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(ExceptionMessage.DB_ERROR));
    }

    private HttpStatus getHttpStatus(HttpStatusCode httpStatusCode) {
        try {
            return HttpStatus.valueOf(httpStatusCode.value());
        } catch (Exception ignored) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}