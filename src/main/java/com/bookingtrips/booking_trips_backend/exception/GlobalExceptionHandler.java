package com.bookingtrips.booking_trips_backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllUncaughtExceptions(Exception ex, WebRequest request) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                "500", "Internal Server Error", null);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error("Request validation error on request: {}", ((ServletWebRequest) request).getRequest().getRequestURI());
        Map<String, String> fieldAndExceptionDetails = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldAndExceptionDetails.put(error.getField(), error.getDefaultMessage());
        }
        String field = fieldAndExceptionDetails.keySet().iterator().next();
        String message = fieldAndExceptionDetails.get(field);

        String errorMessage = String.format("%s %s", field, message);
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("400", errorMessage, fieldAndExceptionDetails);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Entity not found error: {}", ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("404", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    protected ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExists ex){
        log.error("User Already exist in database: {}", ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("404", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenRefreshException.class)
    protected ResponseEntity<Object> handleTokenRefreshException(TokenRefreshException ex){
        log.error("User Already exist in database: {}", ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("404", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MismatchedInputException.class)
    protected ResponseEntity<Object> handleMismatchedInputException(MismatchedInputException ex) {
        log.error("Mismatched input error: {}", ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("400", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IlegalNumberOfSeatsException.class)
    protected ResponseEntity<Object> handleIlegalNumberOfSeatsException(IlegalNumberOfSeatsException ex){
        log.error("Ilegal number of seats error: {}",ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("400", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationAlreadyExists.class)
    protected ResponseEntity<Object> reservationAlreadyExists(ReservationAlreadyExists ex){
        log.error("Ilegal number of seats error: {}",ex.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto("400", ex.getMessage(), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
