# Backend Systems Semester Project

## Table of Contents
- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Database Design](#database-design)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Getting Started](#getting-started)
- [Contributors](#contributors)

## Project Overview
This project is the semester assignment for the course "Backend Systems." The application is a backend API service implementing CRUD operations following a hexagonal architecture, with a focus on clean separation between business logic, API, and data persistence layers.

The project requirements include:
1. Using one of the API technologies discussed in class (e.g., GraphQL, gRPC, Web API, or REST API).
2. Implementing Java Persistence API (JPA) for database interaction.
3. Including one or more entity relationships (e.g., one-to-many).
4. Providing CRUD operations for the entities.
5. Writing comprehensive unit and integration tests.
6. Deploying as a one-click Docker container.

## Tech Stack
- **Programming Language**: Java
- **Framework**: <Framework Choice>
- **Database**: <Database Choice>
- **API Technology**: <API Choice>
- **Containerization**: Docker
- **Testing Frameworks**: JUnit and <Framework Test Choice>

## Architecture
This project follows a hexagonal (or "ports and adapters") architecture. The primary components are:
1. **Domain Layer**: Contains business logic and core models.
2. **Application Layer**: Defines the use cases and handles interactions between the domain and API layers.
3. **Infrastructure Layer**: Manages database interactions using JPA and includes repositories.
4. **API Layer**: Exposes endpoints to interact with the domain through <API Choice>.

## Database Design
The database structure includes entities with a one-to-many relationship. Example relationships could be:
- <>
- <>

This relationship will be represented using JPA annotations and mapped to tables in the database. Entity details and relationships are documented in `/docs/database-design.md`.

## API Endpoints
The API provides endpoints to manage the entities with the following operations:
- **Create**: Add a new entity.
- **Read**: Retrieve an entity or list of entities.
- **Update**: Modify an existing entity.
- **Delete**: Remove an entity.

Endpoint details will be specified in `/docs/api-documentation.md`.

## Testing
Testing is essential to ensure the stability and reliability of the application. The project includes:
1. **Unit Tests**: Validate individual components, especially business logic.
2. **Integration Tests**: Check the complete flow, including database interactions and API responses.

The `/tests` directory contains all test cases with documentation on running them.

## Getting Started
### Prerequisites
- Docker

### One-Click Deployment
1. Clone the repository:
    ```bash
    git clone https://github.com/daskyy/bs-project.git
    ```
2. Build and start the Docker container:
    ```bash
    docker-compose up --build
    ```
3. The application will be accessible on `http://localhost:<Port>`. Adjust configuration in `docker-compose.yml` as needed.

## Contributors
- [@username1](https://github.com/username1)
- [@username2](https://github.com/username2)
- [@username3](https://github.com/username3)
- [@username4](https://github.com/username4)

