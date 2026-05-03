package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.user.dto.CreateUserRequest;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserMapper;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        User user = userMapper.createUserToUser(request);
        user.setCreateAt(Instant.now());

        User savedUser = userRepository.save(user);

        return userMapper.userToUserResponse(savedUser);
    }
}
