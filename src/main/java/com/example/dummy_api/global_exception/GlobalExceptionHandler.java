package com.example.dummy_api.global_exception;

import com.example.dummy_api.exceptions.IdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ErrorMessage handleResourceNotFound(IdNotFoundException ex, HttpServletRequest request) {
        return new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(IOException.class)
    public ErrorMessage defaultException(IOException ex, HttpServletRequest request) {
        return new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage defaultException(Exception ex, HttpServletRequest request) {
        return new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error",
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}
