package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.user.dto.CreateUserRequest;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
}
