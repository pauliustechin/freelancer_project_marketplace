package io.github.pauliustechin.freelancer_marketplace.project.dto;

import io.github.pauliustechin.freelancer_marketplace.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project createProjectToProject(CreateProjectRequest request);

    @Mapping(source = "id", target = "projectId")
    @Mapping(source = "client.id", target = "clientId")
    ProjectResponse projectToProjectResponse(Project project);

    @Mapping(source = "id", target = "projectId")
    ProjectSummaryResponse projectToProjectSummaryResponse(Project project);

}
