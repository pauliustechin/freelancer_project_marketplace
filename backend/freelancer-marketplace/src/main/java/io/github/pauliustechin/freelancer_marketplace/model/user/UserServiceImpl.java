package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.role.AppRole;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.FreelancerResponse;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.UserMapper;
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

        System.out.println(response.getFirst());

        return response;
    }
}
