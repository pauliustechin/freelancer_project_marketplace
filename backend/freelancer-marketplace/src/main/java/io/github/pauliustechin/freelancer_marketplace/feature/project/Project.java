package io.github.pauliustechin.freelancer_marketplace.feature.project;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.feature.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "projects")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    public Project(Long id, String projectName, String description, String projectFileUrl, ProjectStatus projectStatus, LocalDate projectStart, LocalDate projectEnd, Instant createdAt, User client) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.projectFileUrl = projectFileUrl;
        this.projectStatus = projectStatus;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.createdAt = createdAt;
        this.client = client;
    }
}
