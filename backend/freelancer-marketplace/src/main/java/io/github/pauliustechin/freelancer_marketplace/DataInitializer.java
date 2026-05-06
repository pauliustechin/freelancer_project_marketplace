package io.github.pauliustechin.freelancer_marketplace;

import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.model.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.model.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.model.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.model.project.Project;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectStatus;
import io.github.pauliustechin.freelancer_marketplace.model.role.AppRole;
import io.github.pauliustechin.freelancer_marketplace.model.role.Role;
import io.github.pauliustechin.freelancer_marketplace.model.role.RoleRepository;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@Configuration
public class DataInitializer {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BidRepository bidRepository;
    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDataa() {
        return args -> {
            Role bidderRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER)
                    .orElseGet(() -> {
                        Role newUserRole = new Role(AppRole.ROLE_SELLER);
                        return roleRepository.save(newUserRole);
                    });

            Role clientRole = roleRepository.findByRoleName(AppRole.ROLE_CLIENT)
                    .orElseGet(() -> {
                        Role newUserRole = new Role(AppRole.ROLE_CLIENT);
                        return roleRepository.save(newUserRole);
                    });

            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet(() -> {
                        Role newAdminRole = new Role(AppRole.ROLE_ADMIN);
                        return roleRepository.save(newAdminRole);
                    });

            Set<Role> bidderRoles = Set.of(bidderRole);
            Set<Role> clientRoles = Set.of(clientRole);
            Set<Role> adminRoles = Set.of(adminRole);


            if (!userRepository.existsByEmail("client@example.com")) {

                User client = new User("client", "client", "client", "client@example.com", encoder.encode("password"));
                client.setRoles(clientRoles);
                userRepository.save(client);
            }

            if (!userRepository.existsByEmail("admin@example.com")) {

                User admin = new User("admin", "admin", "admin", "admin@example.com", encoder.encode("password"));
                admin.setRoles(adminRoles);
                userRepository.save(admin);
            }

            User user = userRepository.findById(1L)
                            .orElseThrow(() -> new ResourceNotFoundException("User", 1L));

            Project savedProject = projectRepository.save(new Project(null, "projectNameONE", "projectDescriptionONE", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, user));
            projectRepository.save(new Project(null, "projectNameTWO", "projectDescriptionTWO", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, user));
            projectRepository.save(new Project(null, "projectNameTHREE", "projectDescriptionTHREE", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, user));

            if (!userRepository.existsByEmail("bidder@example.com")) {

                User bidder = new User("bidder", "bidder", "bidder", "user@example.com", encoder.encode("password"));
                bidder.setRoles(bidderRoles);
                User savedBidder = userRepository.save(bidder);

                Bid bid = new Bid(BigDecimal.TEN, BidStatus.OPEN, Instant.now(), savedProject, savedBidder);
                bidRepository.save(bid);

            }

            if (!userRepository.existsByEmail("bidder2@example.com")) {

                User bidder2 = new User("bidder2", "bidder2", "bidder2", "user2@example.com", encoder.encode("password"));
                bidder2.setRoles(bidderRoles);
                User savedBidder = userRepository.save(bidder2);

                Bid bid = new Bid(BigDecimal.TWO, BidStatus.OPEN, Instant.now(), savedProject, savedBidder);
                bidRepository.save(bid);
            }

        };
    }
}