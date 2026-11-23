package com.example.academia.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super("Credenciais inválidas.");
    }
}
