package io.github.pauliustechin.freelancer_marketplace.feature.contract;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.feature.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.feature.contract.dto.ContractListResponse;

public interface ContractService {

    Bid confirmContract(BidStatus status, Bid bid);
    ContractListResponse getBidderContracts(Long bidderId);

    ContractListResponse getClientContracts(Long clientId);
}
