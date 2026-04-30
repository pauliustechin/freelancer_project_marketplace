package io.github.pauliustechin.freelancer_marketplace.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long projectId) {
        super("Project with id: " + projectId + " was not found.");
    }
}
