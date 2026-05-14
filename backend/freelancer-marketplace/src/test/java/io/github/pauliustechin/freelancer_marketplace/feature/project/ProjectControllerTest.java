package io.github.pauliustechin.freelancer_marketplace.feature.project;

import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.CreateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.ProjectListResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.ProjectResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.UpdateProjectRequest;
import io.github.pauliustechin.freelancer_marketplace.security.jwt.AuthTokenFilter;
import io.github.pauliustechin.freelancer_marketplace.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private JwtUtils jwtUtils;

    @MockitoBean
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private ProjectResponse projectResponse;
    private UpdateProjectRequest updateProjectRequest;
    private CreateProjectRequest createProjectRequest;
    private ProjectListResponse projectListResponse;

    @BeforeEach
    void setUp() {

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

        projectListResponse = ProjectListResponse.builder()
                .content(List.of(projectResponse))
                .number(0)
                .size(10)
                .totalElements(1L)
                .totalPages(1)
                .first(true)
                .last(true)
                .build();
    }

    @Nested
    @DisplayName("Create Project Controller Tests")
    class CreateProjectControllerTests {

        @Test
        @DisplayName("Should create Project successfully and return ProjectResponse with status code 201.")
        public void shouldCreateProjectAndReturnProjectResponseWith201() throws Exception {

            Long clientId = 1L;
            given(projectService.createProject(any(CreateProjectRequest.class), eq(clientId)))
                    .willReturn(projectResponse);


            mockMvc.perform(post("/api/users/{clientId}/projects", clientId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(createProjectRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.projectId").value(1L))
                    .andExpect(jsonPath("$.projectName").value("projectName"))
                    .andExpect(jsonPath("$.clientId").value(1L));

            verify(projectService, times(1)).createProject(any(), eq(clientId));
        }

        @Test
        @DisplayName("Should return 404 when client is not found.")
        public void shouldReturn404WhenClientNotFound() throws Exception {

            Long clientId = 99L;
            given(projectService.createProject(any(CreateProjectRequest.class), eq(clientId)))
                    .willThrow(new ResourceNotFoundException("Client", clientId));


            mockMvc.perform(post("/api/users/{clientId}/projects", clientId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(createProjectRequest)))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value("Client with id: 99 was not found."));

            verify(projectService, times(1)).createProject(any(), eq(clientId));
        }
    }

    @Test
    @DisplayName("Should return ProjectListResponse with status code 200.")
    public void shouldReturnProjectListResponseWithStatus200() throws Exception {

        given(projectService.searchForProject(eq(ProjectStatus.OPEN), isNull(), isNull(), any(Pageable.class)))
                .willReturn(projectListResponse);

        mockMvc.perform(get("/api/projects")
                        .param("status", "OPEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(1))
                .andExpect(jsonPath("$.content[0].projectStatus").value("OPEN"));

        verify(projectService, times(1))
                .searchForProject(eq(ProjectStatus.OPEN), isNull(), isNull(), any(Pageable.class));

    }

}