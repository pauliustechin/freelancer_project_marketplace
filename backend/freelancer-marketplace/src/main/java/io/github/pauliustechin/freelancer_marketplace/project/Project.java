package io.github.pauliustechin.freelancer_marketplace.project;

import io.github.pauliustechin.freelancer_marketplace.bid.Bid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String description;

    private String projectFileUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private LocalDate projectStart;

    private LocalDate projectEnd;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "project")
    private Set<Bid> bid = new HashSet<>();

    public Project(Long id, String projectName, String description, String projectFileUrl, ProjectStatus projectStatus, LocalDate projectStart, LocalDate projectEnd) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.projectFileUrl = projectFileUrl;
        this.projectStatus = projectStatus;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }
}
