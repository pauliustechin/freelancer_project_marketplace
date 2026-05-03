package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectSummaryResponse;
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
