package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectsListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<ProjectsListResponse> getAllProjects() {

        return ResponseEntity.ok().body(projectService.getAllProjects());
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
