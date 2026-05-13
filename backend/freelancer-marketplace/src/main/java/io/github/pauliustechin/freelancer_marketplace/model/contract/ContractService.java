package io.github.pauliustechin.freelancer_marketplace.model.contract;

import io.github.pauliustechin.freelancer_marketplace.model.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.model.contract.dto.ContractListResponse;

public interface ContractService {

    Bid confirmContract(BidStatus status, Bid bid);
    ContractListResponse getBidderContracts(Long bidderId);

    ContractListResponse getClientContracts(Long clientId);
}
