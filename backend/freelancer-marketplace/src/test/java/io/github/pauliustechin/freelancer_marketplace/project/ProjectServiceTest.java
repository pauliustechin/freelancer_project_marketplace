package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.model.project.Project;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectServiceImpl;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectStatus;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectMapper;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.UpdateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProjectServiceImpl Unit Tests")
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private BidRepository bidRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectResponse projectResponse;
    private UpdateProjectRequest updateProjectRequest;
    private CreateProjectRequest createProjectRequest;
    private User client;

    @BeforeEach
    void setUp() {

        client = User.builder()
                .id(1L)
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("user@email.com")
                .createAt(Instant.now())
                .updatedAt(null)
                .build();

        project = Project.builder()
                .id(1L)
                .projectName("projectName")
                .description("projectDescription")
                .projectFileUrl("https://test.com")
                .projectStatus(ProjectStatus.OPEN)
                .projectStart(LocalDate.of(2026, 06, 01))
                .projectEnd(LocalDate.of(2026, 10, 01))
                .bid(null)
                .client(client)
                .build();

        createProjectRequest = CreateProjectRequest.builder()
                .projectName("projectName")
                .description("projectDescription")
                .projectFileUrl("https://test.com")
                .projectStart(LocalDate.of(2026, 6, 1))
                .projectEnd(LocalDate.of(2026, 10, 1))
                .build();

        updateProjectRequest = UpdateProjectRequest.builder()
                .projectName("projectNameUpdated")
                .description("projectDescriptionUpdated")
                .projectFileUrl("https://testUpdated.com")
                .projectStart(LocalDate.of(2030, 6, 1))
                .projectEnd(LocalDate.of(2050, 10, 1))
                .projectStatus(ProjectStatus.OPEN)
                .build();

        projectResponse = ProjectResponse.builder()
                .projectId(1L)
                .projectName("projectName")
                .description("projectDescription")
                .projectFileUrl("https://test.com")
                .projectStatus(ProjectStatus.OPEN)
                .projectStart(LocalDate.of(2026, 6, 1))
                .projectEnd(LocalDate.of(2026, 10, 1))
                .clientId(1L)
                .build();
    }

    @Nested
    @DisplayName("Create Project Tests")
    class CreateProjectTests {

        @Test
        @DisplayName("Should create project when valid request and client exists")
        void shouldCreateProjectSuccessfully() {

            Long clientId = 1L;
            when(userRepository.findById(clientId)).thenReturn(Optional.of(client));
            when(projectMapper.createProjectToProject(createProjectRequest))
                    .thenReturn(project);
            when(projectRepository.save(any(Project.class))).thenReturn(project);
            when(projectMapper.projectToProjectResponse(project)).thenReturn(projectResponse);

            ProjectResponse result = projectService.createProject(createProjectRequest, clientId);

            assertNotNull(result);
            assertEquals(projectResponse, result);
            verify(userRepository, times(1)).findById(clientId);
            verify(projectMapper, times(1)).createProjectToProject(createProjectRequest);
            verify(projectRepository, times(1)).save(any(Project.class));
            verify(projectMapper, times(1)).projectToProjectResponse(project);
            verify(projectRepository)
                    .save(argThat(pr -> pr != null && pr.getProjectStatus().equals(ProjectStatus.OPEN)));
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when user is not found.")
        void shouldThrowResourceNotFoundExceptionWhenUserNotFound () {
            Long clientId = 1L;
            when(userRepository.findById(clientId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(
                    ResourceNotFoundException.class,
                    () -> projectService.createProject(createProjectRequest, clientId)
            );

            assertEquals("Client with id: " + clientId + " was not found.", exception.getMessage());
            verify(userRepository, times(1)).findById(clientId);
            verify(projectMapper, times(0)).createProjectToProject(createProjectRequest);
            verifyNoInteractions(projectRepository);
        }

        @Test
        @DisplayName("Should handle null clientId in a request.")
        void shouldHandleNullClientIdInRequest() {

            ResourceNotFoundException exception = assertThrows(
                    ResourceNotFoundException.class,
                    () -> projectService.createProject(createProjectRequest, null)
            );

            assertEquals("Client with id: null was not found.", exception.getMessage());
            verifyNoInteractions(userRepository);
            verifyNoInteractions(projectMapper);
            verifyNoInteractions(projectRepository);
        }
    }
}
