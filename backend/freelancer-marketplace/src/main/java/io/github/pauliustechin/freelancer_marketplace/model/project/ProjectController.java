package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<ProjectListResponse> searchForProjects(
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) LocalDate projectStart,
            @PageableDefault(page = 0, size = 10, sort="createdAt") Pageable pageable
    ) {

        ProjectListResponse projectListResponse = projectService.searchForProject(status, projectName, projectStart, pageable);

        return ResponseEntity.ok().body(projectListResponse);
    }

    @PostMapping("/users/{clientId}/projects")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest createRequest,
                                                         @PathVariable Long clientId) {

        ProjectResponse response = projectService.createProject(createRequest, clientId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @Valid @RequestBody UpdateProjectRequest updateRequest) {

        ProjectResponse response = projectService.updateProject(projectId, updateRequest);

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable Long projectId) {

        projectService.deleteProject(projectId);

        return ResponseEntity.noContent().build();

    }

}
