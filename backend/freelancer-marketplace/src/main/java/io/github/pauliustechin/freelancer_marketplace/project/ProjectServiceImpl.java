package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.exception.ProjectNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.project.dto.*;
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
    public ProjectResponse createProject(CreateProjectRequest createRequest) {

        Project project = projectMapper.createProjectToProject(createRequest);
        project.setProjectStatus(ProjectStatus.OPEN);
        Project savedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(savedProject);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest) {

        if(projectId == null) {
            throw new ProjectNotFoundException(projectId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        project.setProjectName(updateRequest.getProjectName());
        project.setDescription(updateRequest.getDescription());
        project.setProjectFileUrl(updateRequest.getProjectFileUrl());
        project.setProjectStart(updateRequest.getProjectStart());
        project.setProjectEnd(updateRequest.getProjectEnd());
        project.setProjectStatus(updateRequest.getProjectStatus());

        Project savedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(savedProject);
    }
}
