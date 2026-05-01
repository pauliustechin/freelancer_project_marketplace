package io.github.pauliustechin.freelancer_marketplace.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class CreateProjectRequest{

    @NotBlank
    private String projectName;

    @NotBlank
    private String description;

    private String projectFileUrl;

    @NotNull
    private LocalDate projectStart;

    private LocalDate projectEnd;
}
