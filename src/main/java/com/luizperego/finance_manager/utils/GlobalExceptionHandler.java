package com.luizperego.finance_manager.utils;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Getter
    @RequiredArgsConstructor
    static class ApiError {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        public ApiError(HttpStatus status, String message, String path) {
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
            this.path = path;
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex, org.springframework.web.context.request.WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getDescription(false).replace("uri=", "");
        ApiError error = new ApiError(status, ex.getMessage(), path);
        return new ResponseEntity<>(error, status);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex, org.springframework.web.context.request.WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getDescription(false).replace("uri=", "");
        
        // Monta mensagem com todos os erros de campo
        String errors = ex.getBindingResult()
                          .getFieldErrors()
                          .stream()
                          .map(FieldError::getDefaultMessage)
                          .collect(Collectors.joining("; "));
        
        ApiError error = new ApiError(status, errors, path);
        return new ResponseEntity<>(error, status);
    }

}
