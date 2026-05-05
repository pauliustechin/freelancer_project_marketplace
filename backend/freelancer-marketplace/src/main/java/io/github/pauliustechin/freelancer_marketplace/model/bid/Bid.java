package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.contract.Contract;
import io.github.pauliustechin.freelancer_marketplace.model.project.Project;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus;

    private Instant createdAt;

    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bidder;

    @OneToOne(mappedBy = "bid")
    private Contract contract;

}
