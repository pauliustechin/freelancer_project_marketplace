package io.github.pauliustechin.freelancer_marketplace.user;

import io.github.pauliustechin.freelancer_marketplace.user.dto.CreateUserRequest;
import io.github.pauliustechin.freelancer_marketplace.user.dto.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
}
