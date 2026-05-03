package io.github.pauliustechin.freelancer_marketplace.security.controller;

import io.github.pauliustechin.freelancer_marketplace.security.jwt.JwtUtils;
import io.github.pauliustechin.freelancer_marketplace.security.request.*;
import io.github.pauliustechin.freelancer_marketplace.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = authService.register(request);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest request) {

        LoginSuccess loginSuccess = authService.authenticate(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, loginSuccess.getCookie().toString())
                .body(loginSuccess.getResponse());

    }



}
