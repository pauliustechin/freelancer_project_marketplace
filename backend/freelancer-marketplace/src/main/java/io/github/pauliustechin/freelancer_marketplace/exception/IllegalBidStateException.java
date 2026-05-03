package io.github.pauliustechin.freelancer_marketplace.exception;

import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;

public class IllegalBidStateException extends RuntimeException {
    public IllegalBidStateException(Long bidId) {
        super("Bid with id: " + bidId + " either canceled or confirmed and can't be modified.");
    }

    public IllegalBidStateException(BidStatus status) {
        super("You are trying to update bid status to " + status +
                ". Bidder can only cancel bid or confirm contract when it's accepted by client.");
    }

    public IllegalBidStateException() {
        super("Client can only accept bids.");
    }

    public IllegalBidStateException(String message) {
        super(message);
    }

}
