package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.Bid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EscrowStatus escrowStatus;

    private BigDecimal agreedAmount;

    private BigDecimal escrowAmount;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid bid;


}
