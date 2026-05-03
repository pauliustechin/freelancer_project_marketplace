package io.github.pauliustechin.freelancer_marketplace.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {
    
    private String message;
    private Instant expiresAt;
    private String email;
    private List<String> roles;

}
