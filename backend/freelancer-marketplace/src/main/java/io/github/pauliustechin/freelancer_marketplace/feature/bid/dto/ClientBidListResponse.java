package io.github.pauliustechin.freelancer_marketplace.feature.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ClientBidListResponse {

    private List<ClientBidResponse> bids;

}
