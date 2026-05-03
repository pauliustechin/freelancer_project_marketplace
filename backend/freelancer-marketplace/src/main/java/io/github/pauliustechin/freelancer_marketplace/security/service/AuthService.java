package io.github.pauliustechin.freelancer_marketplace.security.service;

import io.github.pauliustechin.freelancer_marketplace.security.request.LoginRequest;
import io.github.pauliustechin.freelancer_marketplace.security.request.LoginSuccess;
import io.github.pauliustechin.freelancer_marketplace.security.request.RegisterRequest;
import io.github.pauliustechin.freelancer_marketplace.security.request.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginSuccess authenticate(LoginRequest request);
}
