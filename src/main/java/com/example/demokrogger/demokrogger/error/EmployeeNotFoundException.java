package com.example.demokrogger.demokrogger.error;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

