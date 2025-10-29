package com.app.ecom_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private CustomErrorResponse buildErrorResponse(Exception e, HttpStatus status, WebRequest request, String message) {
        return CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message != null ? message : e.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        CustomErrorResponse customErrorResponse = buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request, null);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request, null);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {

        // Collect validation errors
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage()));

        String errorMessage = "Validation Failed : " + fieldErrors;
        CustomErrorResponse customErrorResponse = buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request, errorMessage);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResponseNotFound(ResourceNotFoundException e, WebRequest request) {
        CustomErrorResponse customErrorResponse = buildErrorResponse(e, HttpStatus.NOT_FOUND, request, null);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}
