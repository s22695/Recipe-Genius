package com.pro.recipe.handler;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ApiError(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
    public ApiError(HttpStatus httpStatus, String message) {
        this(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
    }
}

