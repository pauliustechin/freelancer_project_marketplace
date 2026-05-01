package io.github.pauliustechin.freelancer_marketplace.exception;

public class DuplicateBidException extends RuntimeException {
    public DuplicateBidException(Long projectId) {
        super("User has already placed a bid on project with id " + projectId + ".");
    }
}
