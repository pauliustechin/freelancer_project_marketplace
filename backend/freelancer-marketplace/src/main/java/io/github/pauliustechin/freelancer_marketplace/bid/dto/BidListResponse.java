package io.github.pauliustechin.freelancer_marketplace.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class BidListResponse {

    private List<BidResponse> bids;

}
