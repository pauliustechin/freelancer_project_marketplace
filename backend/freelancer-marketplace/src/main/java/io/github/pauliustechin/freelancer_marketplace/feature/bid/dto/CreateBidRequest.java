package io.github.pauliustechin.freelancer_marketplace.feature.bid.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateBidRequest {

    @NotNull
    @Positive
    private BigDecimal amount;

}
