package io.github.pauliustechin.freelancer_marketplace.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CreateProjectRequest{

    @NotBlank
    private String projectName;

    @NotBlank
    private String description;

    private String projectFileUrl;

    @NotBlank
    private LocalDate projectStart;

    private LocalDate projectEnd;
}
