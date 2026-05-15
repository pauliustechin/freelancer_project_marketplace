CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO roles (role_name)
SELECT 'ROLE_CLIENT'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'ROLE_CLIENT');

INSERT INTO roles (role_name)
SELECT 'ROLE_SELLER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'ROLE_SELLER');

INSERT INTO roles (role_name)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'ROLE_ADMIN');

INSERT INTO users (username, first_name, last_name, email, password, created_at, updated_at)
VALUES
('client', 'client', 'client', 'client@example.com', crypt('password', gen_salt('bf')), now(), now()),
('client2', 'client', 'client', 'client2@example.com', crypt('password', gen_salt('bf')), now(), now()),
('admin', 'admin', 'admin', 'admin@example.com', crypt('password', gen_salt('bf')), now(), now()),
('bidder', 'bidder', 'bidder', 'bidder@example.com', crypt('password', gen_salt('bf')), now(), now()),
('bidder2', 'bidder2', 'bidder2', 'bidder2@example.com', crypt('password', gen_salt('bf')), now(), now())
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1), (2, 1), (3, 3), (4, 2), (5, 2)
ON CONFLICT DO NOTHING;

INSERT INTO projects (project_name, description, project_file_url, project_status, project_start, project_end, created_at, user_id) VALUES
('E-Commerce Admin Dashboard', 'Build a modern admin dashboard for managing products, orders, customers, and analytics.', NULL, 'OPEN', DATE '2026-07-05', NULL, now(), 1),
('Restaurant Reservation System', 'Create a booking platform for restaurants with table management and customer notifications.', NULL, 'OPEN', DATE '2026-07-08', NULL, now(), 1),
('Fitness Tracking App', 'Mobile-friendly fitness tracker with workout logs, calorie tracking, and progress charts.', NULL, 'OPEN', DATE '2026-07-12', NULL, now(), 1),
('Real Estate Listing Platform', 'Platform for listing properties with filters, image galleries, and contact forms.', NULL, 'OPEN', DATE '2026-07-15', NULL, now(), 2),
('Task Management Tool', 'Kanban-based task management system with deadlines and team collaboration features.', NULL, 'OPEN', DATE '2026-07-18', NULL, now(), 2),
('Online Learning Platform', 'E-learning system with courses, quizzes, certificates, and student tracking.', NULL, 'OPEN', DATE '2026-07-20', NULL, now(), 1),
('Portfolio Website Builder', 'Tool for creating modern developer portfolios with templates and customization.', NULL, 'OPEN', DATE '2026-07-22', NULL, now(), 2),
('Event Management System', 'Manage events, ticket sales, registrations, and notifications in one platform.', NULL, 'OPEN', DATE '2026-07-25', NULL, now(), 1),
('Crypto Portfolio Dashboard', 'Track cryptocurrency prices, portfolio value, and market trends in real time.', NULL, 'OPEN', DATE '2026-07-27', NULL, now(), 2),
('Job Board Platform', 'Job marketplace for posting jobs, applying with CVs, and managing applications.', NULL, 'OPEN', DATE '2026-08-01', NULL, now(), 1),
('Travel Booking System', 'Book hotels, flights, and travel packages with secure payment integration.', NULL, 'OPEN', DATE '2026-08-04', NULL, now(), 2),
('Inventory Management System', 'Track stock levels, suppliers, and generate inventory reports.', NULL, 'OPEN', DATE '2026-08-07', NULL, now(), 1),
('AI Chat Assistant Platform', 'AI-powered chatbot for customer support and FAQ automation.', NULL, 'OPEN', DATE '2026-08-10', NULL, now(), 2),
('Medical Appointment System', 'Online booking system for doctors and patients with reminders.', NULL, 'OPEN', DATE '2026-08-14', NULL, now(), 1),
('Social Media Analytics Dashboard', 'Track social media engagement, posts performance, and analytics.', NULL, 'OPEN', DATE '2026-08-18', NULL, now(), 2);


INSERT INTO bids (amount, bid_status, created_at, updated_at, project_id, user_id)
VALUES
(1000.00, 'OPEN', now(), now(), 1, 4),
(1250.00, 'OPEN', now(), now(), 1, 5),
(1500.00, 'OPEN', now(), now(), 2, 4),
(2000.00, 'OPEN', now(), now(), 3, 4),
(2300.00, 'OPEN', now(), now(), 3, 5);







