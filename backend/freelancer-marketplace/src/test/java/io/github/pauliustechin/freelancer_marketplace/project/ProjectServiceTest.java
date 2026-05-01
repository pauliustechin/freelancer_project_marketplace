package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.exception.IllegalProjectStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.project.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project mappedProject;
    private CreateProjectRequest createProjectRequest;
    private Project savedProject;
    private ProjectResponse projectResponse;
    private UpdateProjectRequest updateProjectRequest;

    @BeforeEach
    void setUp() {
        createProjectRequest = new CreateProjectRequest(
                "projectName",
                "projectDescription",
                "https://test.com",
                LocalDate.of(2026, 06, 01),
                LocalDate.of(2026, 10, 01)
        );

        updateProjectRequest = new UpdateProjectRequest(
                "projectNameUpdated",
                "projectDescriptionUpdated",
                "https://testUpdated.com",
                LocalDate.of(2030, 06, 01),
                LocalDate.of(2050, 10, 01),
                ProjectStatus.OPEN
        );

        mappedProject = new Project(null,
                "projectName",
                "projectDescription",
                "https://test.com",
                null,
                LocalDate.of(2026, 06, 01),
                LocalDate.of(2026, 10, 01)
        );

        savedProject = new Project(1L,
                "projectName",
                "projectDescription",
                "https://test.com",
                ProjectStatus.OPEN,
                LocalDate.of(2026, 06, 01),
                LocalDate.of(2026, 10, 01)
        );
        projectResponse = new ProjectResponse(1L,
                "projectName",
                "projectDescription",
                "https://test.com",
                ProjectStatus.OPEN,
                LocalDate.of(2026, 06, 01),
                LocalDate.of(2026, 10, 01)
        );
    }

    @Test
    void getAllProjectsShouldReturnAllProjects() {

        Mockito.when(projectRepository.findAll()).thenReturn(List.of(savedProject));
        Mockito.when(projectMapper.projectToProjectResponse(savedProject)).thenReturn(projectResponse);

        ProjectsListResponse result = projectService.getAllProjects();

        Assertions.assertEquals(1, result.getProjects().size());
        Assertions.assertEquals(result.getProjects().get(0), projectResponse);

    }

//    @Test
//    void createProjectShouldSaveProjectSuccessfullyAndReturnProjectResponse() {
//
//        Mockito.when(projectMapper.createProjectToProject(createProjectRequest)).thenReturn(mappedProject);
//        Mockito.when(projectRepository.save(mappedProject)).thenReturn(savedProject);
//        Mockito.when(projectMapper.projectToProjectResponse(savedProject)).thenReturn(projectResponse);
//
//        ProjectResponse result = projectService.createProject(createProjectRequest);
//
//        Mockito.verify(projectRepository).save(mappedProject);
//        Assertions.assertEquals(result, projectResponse);
//        Assertions.assertEquals(1L, result.getId());
//        Assertions.assertEquals(ProjectStatus.OPEN, result.getProjectStatus());
//
//    }

    @Test
    void shouldThrowException_whenProjectNotFound() {

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> projectService.updateProject(1L, updateProjectRequest));

    }

    @Test
    void shouldThrowException_whenProjectCompleted() {

        savedProject.setProjectStatus(ProjectStatus.COMPLETED);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(savedProject));

        Assertions.assertThrows(IllegalProjectStateException.class,
                () -> projectService.updateProject(1L, updateProjectRequest));

    }

    @Test
    void shouldThrowException_whenProjectCanceled() {

        savedProject.setProjectStatus(ProjectStatus.CANCELED);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(savedProject));

        Assertions.assertThrows(IllegalProjectStateException.class,
                () -> projectService.updateProject(1L, updateProjectRequest));

    }

    @Test
    void shouldThrowException_whenRevertingToOpen() {

        savedProject.setProjectStatus(ProjectStatus.IN_PROGRESS);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(savedProject));

        Assertions.assertThrows(IllegalProjectStateException.class,
                () -> projectService.updateProject(1L, updateProjectRequest));
    }

    @Test
    void shouldUpdateProjectSuccessfully() {

        Project updatedProject = new Project(1L,
                "projectNameUpdated",
                "projectDescriptionUpdated",
                "https://testUpdated.com",
                ProjectStatus.OPEN,
                LocalDate.of(2030, 06, 01),
                LocalDate.of(2050, 10, 01)
        );

        ProjectResponse updatedProjectResponse = new ProjectResponse(1L,
                "projectNameUpdated",
                "projectDescriptionUpdated",
                "https://testUpdated.com",
                ProjectStatus.OPEN,
                LocalDate.of(2030, 06, 01),
                LocalDate.of(2050, 10, 01)
        );

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(savedProject));
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(updatedProject);
        Mockito.when(projectMapper.projectToProjectResponse(updatedProject)).thenReturn(updatedProjectResponse);

        ProjectResponse result = projectService.updateProject(1L, updateProjectRequest);

        Mockito.verify(projectRepository).save(Mockito.any(Project.class));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedProjectResponse, result);
        Assertions.assertEquals("projectNameUpdated", result.getProjectName());
        Assertions.assertEquals(LocalDate.of(2030, 06, 01), result.getProjectStart());
    }

    @Test
    void shouldDeleteProject() {

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(savedProject));

        projectService.deleteProject(1L);

        Mockito.verify(projectRepository).delete(savedProject);
    }

}
