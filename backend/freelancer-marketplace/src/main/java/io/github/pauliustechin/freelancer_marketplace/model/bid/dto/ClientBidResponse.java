package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.FreelancerResponse;
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