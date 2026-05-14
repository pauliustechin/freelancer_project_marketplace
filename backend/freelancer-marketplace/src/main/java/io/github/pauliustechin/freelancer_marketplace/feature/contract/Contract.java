package io.github.pauliustechin.freelancer_marketplace.feature.contract;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.Bid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EscrowStatus escrowStatus;

    private BigDecimal agreedAmount;

    private BigDecimal escrowAmount;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid bid;

}
