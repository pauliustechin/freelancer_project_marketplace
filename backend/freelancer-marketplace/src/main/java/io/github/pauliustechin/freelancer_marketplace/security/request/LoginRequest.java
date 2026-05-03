package io.github.pauliustechin.freelancer_marketplace.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull
    @Email
    String username;

    @NotNull
    String password;

}
