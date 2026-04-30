package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectMapper;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectsListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectsListResponse getAllProjects() {

        List<Project> projects = projectRepository.findAll();
        List<ProjectResponse> projectResponses = projects.stream()
                .map(project -> projectMapper.projectToProjectResponse(project))
                .toList();

        return new ProjectsListResponse(projectResponses);
    }

    @Override
    public ProjectResponse createProject(CreateProjectRequest request) {

        Project project = projectMapper.createProjectToProject(request);
        project.setProjectStatus(ProjectStatus.OPEN);
        Project savedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(savedProject);
    }
}
