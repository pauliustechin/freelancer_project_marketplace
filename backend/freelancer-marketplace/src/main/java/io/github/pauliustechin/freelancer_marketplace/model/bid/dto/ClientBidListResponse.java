package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ClientBidListResponse {

    private List<ClientBidResponse> bids;

}
