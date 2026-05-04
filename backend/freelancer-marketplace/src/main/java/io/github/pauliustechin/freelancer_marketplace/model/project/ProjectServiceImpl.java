package io.github.pauliustechin.freelancer_marketplace.model.project;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalProjectStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.*;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProjectListResponse searchForProject(
            ProjectStatus status,
            String projectName,
            LocalDate startsAfter,
            Pageable pageable
    ) {

        Specification<Project> specification = Specification
                .where(ProjectSpecification.hasStatus(status))
                .and(ProjectSpecification.hasName(projectName))
                .and(ProjectSpecification.startsAfter(startsAfter));

        Page<Project> pageProjects = projectRepository.findAll(specification, pageable);

        Set<Long> projectIds = pageProjects.stream()
                .map(project -> project.getId())
                .collect(Collectors.toSet());

        Map<Long, Long> bidCountByProject = bidRepository.getProjectsWithBidCount(projectIds)
                .stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> (Long) row[1]
                ));


        List<ProjectResponse> projects = pageProjects.stream()
                .map(project -> projectMapper.projectToProjectResponse(project))
                .map(response -> {
                    Long bidCount = bidCountByProject.get(response.getProjectId());
                    response.setBidCount(bidCount != null ? bidCount : 0);
                    return response;
                })
                .toList();

        ProjectListResponse response = new ProjectListResponse();
        response.setContent(projects);
        response.setNumber(pageProjects.getNumber());
        response.setSize(pageProjects.getSize());
        response.setTotalElements(pageProjects.getTotalElements());
        response.setTotalPages(pageProjects.getTotalPages());
        response.setFirst(pageProjects.isFirst());
        response.setLast(pageProjects.isLast());

        return response;
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
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

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
