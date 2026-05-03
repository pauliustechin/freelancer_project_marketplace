package io.github.pauliustechin.freelancer_marketplace.model.contract;

import io.github.pauliustechin.freelancer_marketplace.model.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;

public interface ContractService {

    Bid confirmContract(BidStatus status, Bid bid);
}
