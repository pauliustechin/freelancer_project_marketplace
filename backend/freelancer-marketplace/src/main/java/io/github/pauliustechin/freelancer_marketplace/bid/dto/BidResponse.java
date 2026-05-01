package io.github.pauliustechin.freelancer_marketplace.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BidResponse {

    private Long bidId;
    private BigDecimal amount;
    private BidStatus bidStatus;
    private Long bidderId;
    private ProjectSummaryResponse projectSummaryResponse;

}
