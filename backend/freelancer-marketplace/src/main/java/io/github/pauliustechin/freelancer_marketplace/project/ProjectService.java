package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectsListResponse;

public interface ProjectService {

    ProjectsListResponse getAllProjects();
    ProjectResponse createProject(CreateProjectRequest request);
}
