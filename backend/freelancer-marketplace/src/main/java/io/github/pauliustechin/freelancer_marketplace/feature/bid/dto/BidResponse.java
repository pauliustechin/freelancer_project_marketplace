package io.github.pauliustechin.freelancer_marketplace.feature.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.feature.project.dto.ProjectSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BidResponse {

    private Long bidId;
    private BigDecimal amount;
    private BidStatus bidStatus;
    private ProjectSummaryResponse projectSummary;

}
