package io.github.pauliustechin.freelancer_marketplace.project.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProjectsListResponse {

    private List<ProjectResponse> projects;

}
