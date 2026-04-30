package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectsListResponse;
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
    public ResponseEntity<ProjectResponse> createProject(@RequestBody CreateProjectRequest request) {

        ProjectResponse response = projectService.createProject(request);
        System.out.println(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
