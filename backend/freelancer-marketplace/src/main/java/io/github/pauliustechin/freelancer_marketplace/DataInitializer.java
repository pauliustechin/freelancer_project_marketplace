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

            if (!userRepository.existsByEmail("client2@example.com")) {

                User client = new User("client2", "client", "client", "client2@example.com", encoder.encode("password"));
                client.setRoles(clientRoles);
                userRepository.save(client);
            }

            if (!userRepository.existsByEmail("client3@example.com")) {

                User client = new User("client3", "client", "client", "client3@example.com", encoder.encode("password"));
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

            User user2 = userRepository.findById(2L)
                    .orElseThrow(() -> new ResourceNotFoundException("User", 1L));

            User user3 = userRepository.findById(3L)
                    .orElseThrow(() -> new ResourceNotFoundException("User", 1L));

            Project savedProject = projectRepository.save(new Project(null, "Project Managing System", "We are looking for a freelancer to develop a modern web application for managing projects and client communication. The application should be responsive, user-friendly, and scalable.", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, Instant.now(), user));
//            Project savedProject2 = projectRepository.save(new Project(null, "projectNameTWO", "projectDescriptionTWO", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, user));
//            Project savedProject3 = projectRepository.save(new Project(null, "projectNameTHREE", "projectDescriptionTHREE", null, ProjectStatus.OPEN, LocalDate.of(2026, 07, 01), null, user));
            Project savedProject2 = projectRepository.save(new Project(null, "E-Commerce Dashboard", "Build a modern admin dashboard for managing products, orders, customers, and analytics with responsive design and clean UI.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 5), null, Instant.now(), user));

            Project savedProject3 = projectRepository.save(new Project(null, "Restaurant Booking Platform", "Develop an online reservation system for restaurants with table management, booking calendar, and customer notifications.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 8), null, Instant.now(), user));

            Project savedProject4 = projectRepository.save(new Project(null, "Fitness Tracking App", "Create a fitness application that allows users to track workouts, goals, calories, and progress over time.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 12), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Real Estate Listing Website", "Develop a responsive property listing platform with search filters, image galleries, and contact forms.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 15), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Task Management Tool", "Build a collaborative task management system with Kanban boards, deadlines, and team communication features.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 18), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Online Learning Platform", "Create an e-learning platform with video courses, quizzes, certificates, and student progress tracking.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 20), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Portfolio Website", "Design and develop a modern personal portfolio website with animations, responsive layout, and CMS integration.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 22), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Event Management System", "Build a web application for managing events, ticket sales, attendee registration, and notifications.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 25), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Crypto Tracking Dashboard", "Develop a dashboard for monitoring cryptocurrency prices, portfolio performance, and market trends in real-time.", null, ProjectStatus.OPEN, LocalDate.of(2026, 7, 27), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Job Board Platform", "Create a job marketplace where employers can post jobs and applicants can apply with resumes and profiles.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 1), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Travel Booking Website", "Develop a travel platform for booking hotels, flights, and vacation packages with payment integration.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 4), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Inventory Management System", "Build an inventory tracking solution with stock management, supplier information, and reporting tools.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 7), null,  Instant.now(), user2));

            projectRepository.save(new Project(null, "AI Chat Assistant", "Create an AI-powered customer support assistant integrated with web chat and FAQ management.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 10), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Medical Appointment App", "Develop a healthcare appointment scheduling platform for doctors and patients with reminders and profiles.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 14), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Music Streaming Platform", "Build a music streaming web application with playlists, artist pages, and audio player integration.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 17), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Marketplace Platform", "Create a multi-vendor marketplace where sellers can manage products, orders, and customer communication.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 20), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Weather Forecast App", "Develop a responsive weather application with real-time forecasts, maps, and location-based updates.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 23), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Social Media Dashboard", "Build a platform for scheduling posts, tracking analytics, and managing multiple social media accounts.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 26), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Online Auction System", "Develop an auction platform with live bidding, countdown timers, and secure payment processing.", null, ProjectStatus.OPEN, LocalDate.of(2026, 8, 29), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Expense Tracking App", "Create a finance management application for tracking expenses, budgets, and monthly reports.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 2), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Food Delivery Platform", "Build a food ordering and delivery application with restaurant management and order tracking.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 5), null, Instant.now(), user3));

            projectRepository.save(new Project(null, "Customer Support Portal", "Develop a ticket-based support system with live chat, issue tracking, and admin management tools.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 9), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Video Conference App", "Create a browser-based video meeting platform with chat, screen sharing, and recording functionality.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 12), null, Instant.now(), user2));

            projectRepository.save(new Project(null, "Digital Library System", "Build an online library management system with book borrowing, search, and user account management.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 15), null, Instant.now(), user));

            projectRepository.save(new Project(null, "Pet Adoption Platform", "Develop a responsive web application for pet adoption listings, shelters, and adoption applications.", null, ProjectStatus.OPEN, LocalDate.of(2026, 9, 18), null, Instant.now(), user3));

            if (!userRepository.existsByEmail("bidder@example.com")) {

                User bidder = new User("bidder", "bidder", "bidder", "user@example.com", encoder.encode("password"));
                bidder.setRoles(bidderRoles);
                User savedBidder = userRepository.save(bidder);

                Bid bid = new Bid(BigDecimal.TEN, BidStatus.OPEN, Instant.now(), savedProject, savedBidder);
                bidRepository.save(bid);

                Bid bid2 = new Bid(BigDecimal.ONE, BidStatus.OPEN, Instant.now(), savedProject2, savedBidder);
                bidRepository.save(bid2);

            }

            if (!userRepository.existsByEmail("bidder2@example.com")) {

                User bidder2 = new User("bidder2", "bidder2", "bidder2", "user2@example.com", encoder.encode("password"));
                bidder2.setRoles(bidderRoles);
                User savedBidder = userRepository.save(bidder2);

                Bid bid = new Bid(BigDecimal.TWO, BidStatus.OPEN, Instant.now(), savedProject, savedBidder);
                bidRepository.save(bid);

                Bid bid2 = new Bid(BigDecimal.TEN, BidStatus.OPEN, Instant.now(), savedProject3, savedBidder);
                bidRepository.save(bid2);
            }

        };
    }
}