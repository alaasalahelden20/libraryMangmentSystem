package com.example.library.exception;

public class PatronNotFoundException extends RuntimeException {
    public PatronNotFoundException(Long id) {
        super("Patron not found with ID: " + id);
    }
}
