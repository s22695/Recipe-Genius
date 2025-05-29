package com.pro.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 401, 403, 404, 409… every HttpClientErrorException from RestTemplate
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public Mono<ResponseEntity<ApiError>> handleHttpClientError(HttpClientErrorException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String raw = ex.getResponseBodyAsString();
        String msg = (raw != null && !raw.isBlank()) ? raw : ex.getStatusText();
        log.error("{} {} from external service: {}", status.value(), status.getReasonPhrase(), msg, ex);

        ApiError body = new ApiError(status, msg);
        return Mono.just(ResponseEntity
                .status(status)
                .body(body));
    }

    // 500 Internal Server Error – catch-all
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiError>> handleAll(Exception ex) {
        log.error("500 Internal Server Error", ex);
        ApiError body = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body));
    }
}

