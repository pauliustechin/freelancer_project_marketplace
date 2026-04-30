package io.github.pauliustechin.freelancer_marketplace.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import java.math.BigDecimal;

public class UpdateBidRequest {

    private BigDecimal amount;
    private BidStatus bidStatus;

}
