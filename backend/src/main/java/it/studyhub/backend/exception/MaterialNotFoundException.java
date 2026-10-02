package it.studyhub.backend.exception;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(String message) {
        super(message);
    }

}
