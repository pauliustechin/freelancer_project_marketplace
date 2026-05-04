package io.github.pauliustechin.freelancer_marketplace.model.project.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectListResponse {

    private List<ProjectResponse> content;
    private Integer number;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private boolean first;
    private boolean last;

}
