package io.github.pauliustechin.freelancer_marketplace.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private String description;

    private String projectFileUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private LocalDate projectStart;

    private LocalDate projectEnd;

}
