package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.user.dto.FreelancerResponse;

import java.util.List;

public interface UserService {

    List<FreelancerResponse> getAllFreelancers();
}
