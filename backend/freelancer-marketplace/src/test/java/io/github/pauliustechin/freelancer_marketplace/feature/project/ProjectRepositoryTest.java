package io.github.pauliustechin.freelancer_marketplace.feature.project;

import io.github.pauliustechin.freelancer_marketplace.feature.user.User;
import io.github.pauliustechin.freelancer_marketplace.feature.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16.0");

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private Project project;
    private User client;

    @BeforeEach
    void setUp() {

        client = User.builder()
                .id(null)
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("user@email.com")
                .createAt(null)
                .updatedAt(null)
                .build();

        project = Project.builder()
                .id(null)
                .projectName("projectName")
                .description("projectDescription")
                .projectFileUrl("https://test.com")
                .projectStatus(ProjectStatus.OPEN)
                .projectStart(LocalDate.of(2026, 06, 01))
                .projectEnd(LocalDate.of(2026, 10, 01))
                .bid(null)
                .client(client)
                .build();
    }

    @Test
    @DisplayName("Should save project successfully then return saved project.")
    void shouldSaveProjectSuccessfullyThenReturnSavedProject() {

        userRepository.save(client);

        Project savedProject = projectRepository.save(project);

        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getId()).isNotNull();
        assertThat(savedProject.getProjectName()).isEqualTo("projectName");

    }

    @Test
    @DisplayName("Should return empty when project is not found.")
    void shouldReturnEmptyWhenProjectNotFound() {

        Optional<Project> result = projectRepository.findById(99L);

        assertThat(result).isEmpty();

    }

}