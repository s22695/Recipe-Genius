package com.pro.user_data.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiError handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .collect(Collectors.joining("; "));
        return new ApiError(HttpStatus.BAD_REQUEST, detail);
    }

    //400: walidacja manualna (ConstraintViolation)
    @ExceptionHandler(ConstraintViolationException.class)
    ApiError handleConstraintViolation(ConstraintViolationException ex) {
        String detail = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining("; "));
        return new ApiError(HttpStatus.BAD_REQUEST, detail);
    }

    //400: zły JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ApiError handleUnreadable(HttpMessageNotReadableException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Niepoprawny JSON: " + ex.getMostSpecificCause().getMessage());
    }

    //404: brak danych
    @ExceptionHandler(EntityNotFoundException.class)
    ApiError handleNotFound(EntityNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ApiError handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Sciezka nie istnieje.");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ApiError handleMethodNotSupported2(HttpRequestMethodNotSupportedException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Sciezka nie istnieje.");
    }

    //409: naruszenie klucza / unikalności
    @ExceptionHandler(DataIntegrityViolationException.class)
    ApiError handleIntegrity(DataIntegrityViolationException ex) {
        return new ApiError(HttpStatus.CONFLICT, ex.getRootCause() != null
                ? ex.getRootCause().getMessage()
                : ex.getMessage());
    }

    //500: pozostałe
    @ExceptionHandler(Exception.class)
    ApiError handleGeneric(Exception ex, HttpServletRequest req) {
        ex.printStackTrace();
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Nieoczekiwany blad w  " + req.getRequestURI());
    }

    private String formatFieldError(FieldError fe) {
        return fe.getField() + " " + fe.getDefaultMessage();
    }
}

