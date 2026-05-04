package io.github.pauliustechin.freelancer_marketplace.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, Long projectId) {
        super(entityName + " with id: " + projectId + " was not found.");
    }

    public ResourceNotFoundException(String entity) {
        super(entity + " must not be null");
    }
}
