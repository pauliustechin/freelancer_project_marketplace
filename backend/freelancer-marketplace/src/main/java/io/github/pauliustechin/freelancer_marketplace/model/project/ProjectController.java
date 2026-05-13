package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")

public class ProjectController {

    private final ProjectService projectService;

    @Tag(name = "Public APIs", description = "APIs for managing public requests")
    @Operation(summary = "Get projects depending on search criteria")
    @GetMapping("/projects")
    public ResponseEntity<ProjectListResponse> searchForProjects(
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) LocalDate projectStart,
            @ParameterObject @PageableDefault(page = 0, size = 10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        ProjectListResponse projectListResponse = projectService.searchForProject(status, projectName, projectStart, pageable);

        return ResponseEntity.ok().body(projectListResponse);
    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Get client projects")
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/users/{clientId}/projects")
    public ResponseEntity<List<ProjectResponse>> getClientProjects(Authentication authentication) {

        List<ProjectResponse> response = projectService.getClientProjects(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Create project")
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/users/{clientId}/projects")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest createRequest,
                                                         @PathVariable Long clientId) {

        ProjectResponse response = projectService.createProject(createRequest, clientId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Update Project")
    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @Valid @RequestBody UpdateProjectRequest updateRequest) {

        ProjectResponse response = projectService.updateProject(projectId, updateRequest);

        return ResponseEntity.ok().body(response);

    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Delete project")
    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {

        projectService.deleteProject(projectId);

        return ResponseEntity.noContent().build();

    }

}
