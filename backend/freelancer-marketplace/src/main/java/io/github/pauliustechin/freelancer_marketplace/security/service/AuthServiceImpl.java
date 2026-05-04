package io.github.pauliustechin.freelancer_marketplace.security.service;

import io.github.pauliustechin.freelancer_marketplace.exception.DuplicateEmailException;
import io.github.pauliustechin.freelancer_marketplace.exception.DuplicateUsernameException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.exception.UserAuthenticationException;
import io.github.pauliustechin.freelancer_marketplace.model.role.AppRole;
import io.github.pauliustechin.freelancer_marketplace.model.role.Role;
import io.github.pauliustechin.freelancer_marketplace.model.role.RoleRepository;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserMapper;
import io.github.pauliustechin.freelancer_marketplace.security.jwt.JwtUtils;
import io.github.pauliustechin.freelancer_marketplace.security.request.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException(request.getUsername());
        } else if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        Role role = roleRepository.findByRoleName(request.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role", null));

        User user = userMapper.registerUserToUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCreateAt(Instant.now());

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Set<AppRole> roleNames = roles.stream()
                .map(r -> role.getRoleName())
                .collect(Collectors.toSet());

        User savedUser = userRepository.save(user);

        logger.info("User registered successfully: userId={}, username={}, email={}", user.getId(), user.getUsername(), user.getEmail());

        RegisterResponse response = userMapper.userRegisterToResponse(savedUser);
        response.setRoleNames(roleNames);

        return response;
    }

    @Override
    @Transactional
    public LoginSuccess authenticate(LoginRequest request) {

        Authentication authentication;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException exception) {
            throw new UserAuthenticationException("Invalid username or password");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        AuthResponse response = new AuthResponse(
                "Auth successful",
                Instant.now().plus(Duration.ofHours(jwtCookie.getMaxAge().toHours())),
                userDetails.getUsername(),
                userDetails.getId(),
                roles);

        logger.info("User logged in successfully: userId={}, username={}", userDetails.getId(), userDetails.getUsername());

        return new LoginSuccess(response, jwtCookie);
    }



}
