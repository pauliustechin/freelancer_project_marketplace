package io.github.pauliustechin.freelancer_marketplace.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProjectResponse {

    private Long projectId;

    private String projectName;

    private String description;

    private String projectFileUrl;

    private LocalDate projectStart;

    private LocalDate projectEnd;
}
