package io.github.pauliustechin.freelancer_marketplace.exception;

public class NotAllowedApiActionException extends RuntimeException {
    public NotAllowedApiActionException(String message) {
        super(message);
    }
}
