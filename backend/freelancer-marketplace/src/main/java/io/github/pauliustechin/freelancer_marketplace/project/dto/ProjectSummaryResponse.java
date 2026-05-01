package io.github.pauliustechin.freelancer_marketplace.project.dto;

import io.github.pauliustechin.freelancer_marketplace.project.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectSummaryResponse {
    private Long projectId;
    private String projectName;
    private ProjectStatus projectStatus;
    private String projectFileUrl;
    private LocalDate projectStart;
}
