package com.pro.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public Mono<ResponseEntity<ApiError>> handleHttpClient(HttpClientErrorException ex) {

        HttpStatus status = (HttpStatus) ex.getStatusCode();      // 400, 401, 403…
        String body       = ex.getResponseBodyAsString();

        String msg = (body != null && !body.isBlank())
                ? body
                : ex.getStatusText();

        log.warn("Keycloak {} {}: {}", status.value(), status.getReasonPhrase(), msg);

        return build(status, msg);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public Mono<ResponseEntity<ApiError>> handleKeycloak(WebClientResponseException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String msg = ex.getResponseBodyAsString();
        if (msg == null || msg.isBlank()) msg = status.getReasonPhrase();
        return build(status, msg);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ApiError>> handleNotFound(ResponseStatusException ex) {
        if (ex.getStatusCode() != HttpStatus.NOT_FOUND) throw ex;
        return build(HttpStatus.NOT_FOUND, "Ścieżka nie istnieje");
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiError>> handleGeneric(Exception ex) {
        log.error("Nieobsłużony wyjątek", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Wewnętrzny błąd serwera");
    }

    private Mono<ResponseEntity<ApiError>> build(HttpStatus status, String msg) {
        ApiError body = new ApiError(status, msg);
        return Mono.just(ResponseEntity.status(status).body(body));
    }
}

