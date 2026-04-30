package io.github.pauliustechin.freelancer_marketplace.exception;

import io.github.pauliustechin.freelancer_marketplace.project.ProjectStatus;

public class ProjectImmutableException extends RuntimeException {
    public ProjectImmutableException(Long projectId, ProjectStatus status) {
        super("Project with id: " + projectId + " is " + status + " and cannot be updated.");
    }

    public ProjectImmutableException(Long projectId, ProjectStatus projectStatus, ProjectStatus updateStatus) {
        super("Project with id: " + projectId + " is " + projectStatus + " and cannot be set to " + updateStatus + ".");
    }
}
