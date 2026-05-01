package io.github.pauliustechin.freelancer_marketplace.project.dto;

import io.github.pauliustechin.freelancer_marketplace.project.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class UpdateProjectRequest {

    @NotBlank
    private String projectName;

    @NotBlank
    private String description;

    private String projectFileUrl;

    @NotNull
    private LocalDate projectStart;

    private LocalDate projectEnd;

    @NotNull
    private ProjectStatus projectStatus;

}
