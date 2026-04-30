package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectsListResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.UpdateProjectRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ProjectsListResponse> getAllProjects() {

        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest createRequest) {

        ProjectResponse response = projectService.createProject(createRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @Valid @RequestBody UpdateProjectRequest updateRequest) {

        ProjectResponse response = projectService.updateProject(projectId, updateRequest);

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable Long projectId) {

        projectService.deleteProject(projectId);

        return ResponseEntity.noContent().build();

    }




}
