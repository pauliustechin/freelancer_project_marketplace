package io.github.pauliustechin.freelancer_marketplace.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateBidRequest {

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private BidStatus status;

}
