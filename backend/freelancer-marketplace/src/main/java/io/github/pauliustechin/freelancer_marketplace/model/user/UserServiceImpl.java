package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
