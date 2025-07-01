package com.Employee.EmployeeMng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // handle resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    //handle resource already exists
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<String> handleResourceAlreadyExists(ResourceAlreadyExistException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT );
    }
}
