package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectsListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;

public interface ProjectService {

    ProjectsListResponse getAllProjects();
    ProjectResponse createProject(CreateProjectRequest createRequest, Long clientId);
    ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest);
    void deleteProject(Long projectId);
}
