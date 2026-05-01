package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;

public interface ContractService {

    Bid confirmContract(BidStatus status, Bid bid);
}
