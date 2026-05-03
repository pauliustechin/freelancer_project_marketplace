package io.github.pauliustechin.freelancer_marketplace.model.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProjectsListResponse {

    private List<ProjectResponse> projects;

}
