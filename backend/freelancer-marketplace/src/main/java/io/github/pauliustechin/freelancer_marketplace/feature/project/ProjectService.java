package io.github.pauliustechin.freelancer_marketplace.feature.project;

import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.ProjectListResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.UpdateProjectRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(CreateProjectRequest createRequest, Long clientId);
    ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest);
    void deleteProject(Long projectId);
    ProjectListResponse searchForProject(ProjectStatus status, String projectName, LocalDate projectStart, Pageable pageable);
    List<ProjectResponse> getClientProjects(Authentication authentication);
}
