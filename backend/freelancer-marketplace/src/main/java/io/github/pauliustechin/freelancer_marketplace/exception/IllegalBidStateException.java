package io.github.pauliustechin.freelancer_marketplace.exception;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;

public class IllegalBidStateException extends RuntimeException {
    public IllegalBidStateException(Long bidId) {
        super("Bid with id: " + bidId + " is not valid any more and cannot be updated.");
    }

    public IllegalBidStateException(BidStatus status) {
        super("You are trying update bid status to " + status + ". Bidder can only cancel bid.");
    }

    public IllegalBidStateException() {
        super("Client can only accept bids.");
    }

}
