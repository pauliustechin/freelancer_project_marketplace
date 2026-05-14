package io.github.pauliustechin.freelancer_marketplace.feature.project.dto;

import io.github.pauliustechin.freelancer_marketplace.feature.project.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private Long projectId;

    private String projectName;

    private String description;

    private String projectFileUrl;

    private ProjectStatus projectStatus;

    private LocalDate projectStart;

    private LocalDate projectEnd;

    private Long clientId;

    private long bidCount;
}
