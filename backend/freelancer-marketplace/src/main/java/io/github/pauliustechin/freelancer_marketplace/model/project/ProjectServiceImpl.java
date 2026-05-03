package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalProjectStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.*;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectsListResponse getAllProjects() {

        List<Project> projects = projectRepository.findAll();
        List<ProjectResponse> projectResponses = projects.stream()
                .map(project -> projectMapper.projectToProjectResponse(project))
                .toList();

        return new ProjectsListResponse(projectResponses);
    }

    @Transactional
    @Override
    public ProjectResponse createProject(CreateProjectRequest createRequest, Long clientId) {

        if(clientId == null) {
            throw new ResourceNotFoundException("Client", clientId);
        }

        User user = userRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));

        Project project = projectMapper.createProjectToProject(createRequest);
        project.setProjectStatus(ProjectStatus.OPEN);
        project.setClient(user);
        project.setCreatedAt(Instant.now());
        Project savedProject = projectRepository.save(project);

        ProjectResponse response = projectMapper.projectToProjectResponse(savedProject);
        response.setClientId(user.getId());

        return response;
    }

    @Transactional
    @Override
    public ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateRequest) {

        if(projectId == null) {
            throw new ResourceNotFoundException("Project", projectId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Procject", projectId));

        ProjectStatus projectStatus = project.getProjectStatus();
        ProjectStatus updateStatus = updateRequest.getProjectStatus();
        if(projectStatus.equals(ProjectStatus.COMPLETED) || projectStatus.equals(ProjectStatus.CANCELED)) {
            throw new IllegalProjectStateException(projectId, projectStatus);
        } else if(projectStatus.equals(ProjectStatus.IN_PROGRESS) && updateStatus.equals(ProjectStatus.CANCELED)) {
            throw new IllegalProjectStateException(projectId, projectStatus, updateStatus);
        } else if(projectStatus.equals(ProjectStatus.OPEN) && !(updateStatus.equals(ProjectStatus.CANCELED))) {
            throw new IllegalProjectStateException();
        }

        project.setProjectName(updateRequest.getProjectName());
        project.setDescription(updateRequest.getDescription());
        project.setProjectFileUrl(updateRequest.getProjectFileUrl());
        project.setProjectStart(updateRequest.getProjectStart());
        project.setProjectEnd(updateRequest.getProjectEnd());
        project.setProjectStatus(updateRequest.getProjectStatus());
        project.setUpdatedAt(Instant.now());

        Project savedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(savedProject);
    }

    @Transactional
    @Override
    public void deleteProject(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        bidRepository.deleteByProjectId(projectId);
        projectRepository.delete(project);
    }
}
