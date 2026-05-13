package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ClientBidListResponse {

    private List<ClientBidResponse> bids;

}
