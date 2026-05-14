package io.github.pauliustechin.freelancer_marketplace.feature.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.feature.user.dto.FreelancerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ClientBidResponse {

    private Long bidId;
    private BigDecimal amount;
    private BidStatus bidStatus;
    private FreelancerResponse freelancer;

}