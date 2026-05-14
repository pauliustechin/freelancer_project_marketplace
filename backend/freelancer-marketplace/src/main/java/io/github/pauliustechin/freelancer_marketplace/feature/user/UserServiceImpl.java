package io.github.pauliustechin.freelancer_marketplace.feature.user;

import io.github.pauliustechin.freelancer_marketplace.feature.role.AppRole;
import io.github.pauliustechin.freelancer_marketplace.feature.user.dto.FreelancerResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<FreelancerResponse> getAllFreelancers() {

        List<User> freelancers = userRepository.findUsersByRole(AppRole.ROLE_SELLER);
        List<FreelancerResponse> response = freelancers.stream()
                .map(user -> {
                    return userMapper.userToFreelancerResponse(user);
                })
                .toList();

        return response;
    }
}
