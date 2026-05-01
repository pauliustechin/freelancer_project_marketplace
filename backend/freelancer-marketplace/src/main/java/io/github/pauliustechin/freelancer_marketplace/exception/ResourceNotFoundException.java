package io.github.pauliustechin.freelancer_marketplace.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String className, Long projectId) {
        super(className + " with id: " + projectId + " was not found.");
    }
}
