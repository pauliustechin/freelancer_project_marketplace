package io.github.pauliustechin.freelancer_marketplace.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("User with email '" + email +"' already exists." );
    }
}
