package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.contract.dto.ContractResponse;

public interface ContractService {

    ContractResponse confirmContract(Long bidId, BidStatus bidStatus);
}
