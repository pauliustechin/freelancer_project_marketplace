package io.github.pauliustechin.freelancer_marketplace;

import io.github.pauliustechin.freelancer_marketplace.model.project.Project;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Autowired
    private ProjectRepository projectRepository;

    @Bean
    CommandLineRunner initDataa() {
        return args -> {
            projectRepository.save(new Project(null, "projectNameONE", "projectDescriptionONE", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null));
            projectRepository.save(new Project(null, "projectNameTWO", "projectDescriptionTWO", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null));
            projectRepository.save(new Project(null, "projectNameTHREE", "projectDescriptionTHREE", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null));
        };
    }
}