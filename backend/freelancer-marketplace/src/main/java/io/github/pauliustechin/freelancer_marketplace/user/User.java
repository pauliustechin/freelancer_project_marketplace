package io.github.pauliustechin.freelancer_marketplace.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Instant createAt;

    private Instant updatedAt;

//    @OneToMany(mappedBy = "user")
//    private Set<Project> projects = new HashSet<>();
//
//    @OneToMany(mappedBy = "user")
//    private Set<Bid> bids = new HashSet<>();

}
