package io.github.pauliustechin.freelancer_marketplace.exception;

import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectStatus;

public class IllegalProjectStateException extends RuntimeException {
    public IllegalProjectStateException(Long projectId, ProjectStatus status) {
        super("Project with id: " + projectId + " is " + status + " and cannot be updated anymore.");
    }

    public IllegalProjectStateException() {
        super("Project status can be set to CANCELED only.");
    }
}
