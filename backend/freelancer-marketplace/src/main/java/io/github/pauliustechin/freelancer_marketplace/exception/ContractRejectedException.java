package io.github.pauliustechin.freelancer_marketplace.exception;

public class ContractRejectedException extends RuntimeException {
    public ContractRejectedException() {
        super("Contract has been rejected by bidder.");
    }
}
