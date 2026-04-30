package io.github.pauliustechin.freelancer_marketplace.project.dto;

import io.github.pauliustechin.freelancer_marketplace.project.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project createProjectToProject(CreateProjectRequest request);
    ProjectResponse projectToProjectResponse(Project project);

}
