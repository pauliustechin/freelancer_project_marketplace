package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectsListResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.UpdateProjectRequest;

public interface ProjectService {

    ProjectsListResponse getAllProjects();
    ProjectResponse createProject(CreateProjectRequest createRequest);
    ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest);
}
