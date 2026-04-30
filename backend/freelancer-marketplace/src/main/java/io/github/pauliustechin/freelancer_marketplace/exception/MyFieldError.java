package io.github.pauliustechin.freelancer_marketplace.exception;

public class MyFieldError {

    private final String field;
    private final String message;

    public MyFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
