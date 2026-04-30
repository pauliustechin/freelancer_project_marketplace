package io.github.pauliustechin.freelancer_marketplace.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateBidRequest {

    private BigDecimal amount;

}
