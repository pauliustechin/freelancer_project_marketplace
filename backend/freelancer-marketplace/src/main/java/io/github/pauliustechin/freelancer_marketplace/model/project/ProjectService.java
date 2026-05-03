package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ProjectService {

    ProjectResponse createProject(CreateProjectRequest createRequest, Long clientId);
    ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest);
    void deleteProject(Long projectId);
    ProjectListResponse searchForProject(ProjectStatus status, String projectName, LocalDate projectStart, Pageable pageable);
}
