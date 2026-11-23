package com.example.academia.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String resource) {
        super("Já existe um(a) " + resource + " com os dados informados.");
    }
}
