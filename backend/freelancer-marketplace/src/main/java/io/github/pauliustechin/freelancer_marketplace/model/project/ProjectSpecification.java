package io.github.pauliustechin.freelancer_marketplace.model.project;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ProjectSpecification {

    public static Specification<Project> hasStatus(ProjectStatus status) {
        return (((root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("projectStatus"), status)));
    }

    public static Specification<Project> hasName(String name) {
        return (((root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("projectName")),
                                                    "%" + name.toLowerCase() + "%")));
    }

    public static Specification<Project> startsAfter(LocalDate date) {
        return (((root, query, criteriaBuilder) ->
                date == null ? null : criteriaBuilder.greaterThan(root.get("projectStart"), date)));
    }

}
