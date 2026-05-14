package io.github.pauliustechin.freelancer_marketplace.feature.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BidListResponse {

    private List<BidResponse> bids;
}
