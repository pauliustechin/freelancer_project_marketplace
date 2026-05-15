## Freelancer Marketplace project (MVP – Core Features)

This project is a full-stack MVP of a freelancer marketplace platform designed to connect companies with freelancers through a structured bidding system.

The system allows users to register, log in, and log out using secure cookie-based authentication. Once authenticated, users can browse available projects and interact with the platform based on their role.

Freelancers can view projects and submit bids, while companies can create and manage their own projects. Companies have the ability to review incoming bids and accept the most suitable offer.

When a bid is accepted by a company, the freelancer must confirm it. After confirmation, a contract is automatically created, establishing a formal agreement between both parties. Contracts are then accessible to both freelancers and companies through their respective dashboards.

The application demonstrates core marketplace functionality, including role-based access control, bidding workflows, and contract lifecycle management.

### Tech Stack
#### Frontend:
- React
- Vite
- JavaScript
- Axios
- React Router

#### Backend:
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA (Hibernate ORM)
- PostgreSQL
- JWT Authentication
- Unit & Integration Testing (JUnit, Mockito, Testcontainers)
- Maven
- Swagger / OpenAPI

#### Infrastructure:
- Docker
- Docker Compose
- Nginx

### System Architecture

```txt
Browser

↓ 

React Frontend (Nginx)

↓ HTTP (REST API)

Spring Boot Backend

↓ JDBC

PostgreSQL Database
```

### Project Structure

```txt
project-root/
├── frontend/freelancer-marketplace        # React frontend
│   ├── src/                               # Application source code
│   ├── public/                            # Static assets
│   └── Dockerfile                         # Dockerfile for building frontend image
│
├── backend/freelancer-marketplace         # Spring Boot backend
│   ├── src/                               # Application source code
│   └── Dockerfile                         # Dockerfile for building backend image
│
├── docker-compose.yml                     # Orchestrates frontend, backend, and database
│
└── README.md                              # Project documentation
```

### Prerequisites
Install the following before running the project:

- Docker
- Visual Studio Code (recommended)
- Git
- At least 480 MB of free disk space

### Run application

In IDE's terminal window:
>git clone https://github.com/pauliustechin/freelancer_marketplace.git

>cd freelancer_marketplace

Run Docker containers in the background (make sure Docker is up and running):
>docker compose up -d

Browser:

> http://localhost:3000

The application is pre-populated with dummy data, including users, their projects, and associated bids to demonstrate core functionality.

Users:
```txt
{
    "username" : "client",
    "password": "password",
    "roles": ["ROLE_CLIENT"]
},
{
    "username" : "client2",
    "password": "password",
    "roles": ["ROLE_CLIENT"]
},
{
    "username" : "admin",
    "password": "password",
    "roles": ["ROLE_ADMIN"]
},
{
    "username" : "bidder",
    "password": "password",
    "roles": ["ROLE_SELLER"]
},
{
    "username" : "bidder",
    "password": "password",
    "roles": ["ROLE_SELLER"]
}
```

### Future Improvements
- Admin panel for user/project moderation
- Additional fields in project model
- Email notifications (bid accepted / contract created)
- File uploads for project attachments
- Redis caching for frequently accessed project listings
- Refresh token authentication flow

### Known Issues
- Some UI data is currently hardcoded in the frontend for MVP purposes
- No rate limiting
- No refresh token support

### Learning Goals

This project was built to gain practical experience in:

- Designing full-stack architecture with React and Spring Boot
- Building RESTful APIs and role-based access control
- Implementing authentication using cookies
- Working with relational databases (PostgreSQL + JPA/Hibernate)
- Containerizing applications using Docker and Docker Compose
- Deploying multi-service applications (frontend, backend, database)
- Writing unit and integration tests