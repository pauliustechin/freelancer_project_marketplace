package io.github.pauliustechin.freelancer_marketplace.feature.user;

import io.github.pauliustechin.freelancer_marketplace.feature.user.dto.FreelancerResponse;

import java.util.List;

public interface UserService {

    List<FreelancerResponse> getAllFreelancers();
}
