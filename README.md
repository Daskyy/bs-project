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
- **Database**: PostgreSQL
- **API Technology**: GraphQL
- **Containerization**: Docker, Docker Compose
- **Testing Frameworks**: JUnit

## Architecture
This project follows a hexagonal (or "ports and adapters") architecture. The primary components are:
1. **Domain Layer**: Contains business logic and core models.
2. **Application Layer**: Defines the use cases and handles interactions between the domain and API layers.
3. **Infrastructure Layer**: Manages database interactions using JPA and includes repositories.
4. **API Layer**: Exposes endpoints to interact with the domain through GraphQL.

## Database Design
The database structure includes entities with multiple types of relationships.
This relationship will be represented using JPA annotations and mapped to tables in the database. 

## API Endpoints
The API provides endpoints to manage the entities with basic aswell as more advanced CRUD operations, such as refunding all users in case of a cancellation, discounts and more.

## Testing
Testing is essential to ensure the stability and reliability of the application. The project includes:
1. **Unit Tests**: Validate individual components, especially business logic.
2. **Integration Tests**: Check the complete flow, including database interactions and API responses.

## Getting Started
### Prerequisites for one-click deployment:
- Docker
- Docker Compose

# 🚀 One-Click Deployment

## 1️⃣ Clone the Repository
```bash
git clone https://github.com/daskyy/bs-project.git
cd bs-project
```

## 2️⃣ Build and Start the Database and Docker Container
```bash
docker-compose up --build -d
```

### 🛠 Application Access
- Once started, the application will be available at:  
  **`http://localhost:8080/graphql`**
and you should see two new containers running when you run `docker ps`.
- Configuration (e.g., database credentials) can be adjusted in `docker-compose.yml` and `docker-compose.db.yml`.

---

## 🧪 Testing
- **Tests can be ran inside the container with the following command:** 
```bash
docker exec -it eventmanager mvn test
```
- **You will then see the console output of the tests.** If you later want to access them, you can find the test reports in the `target/surefire-reports` directory.
- Note: Tests **cannot** be run within the IDE due to the containerized environment.
---

## 🔴 Stopping the Application
If you need to stop the application:

```bash
docker-compose down
```
This will **stop and remove** the eventmanager container **but keep the database volumes and container**.

### 🧹 Full Cleanup (Including Database)
If you want to **completely reset** the environment, including database data:

```bash
docker-compose down -v --remove-orphans
```
You will then have to rebuild and restart the database and application. (see steps above)

---

## ▶️ Restarting the Application After Stopping
If you've previously stopped the application container or the database container and want to restart it:

```bash
docker-compose up
```

If you made changes and need to **rebuild before restarting**, use:

```bash
docker-compose build --no-cache -d
```
---

## Contributors
- [@Daskyy](https://github.com/Daskyy)
- [@SebasN12](https://github.com/SebasN12)
- [@mvx-320](https://github.com/mvx-320)
- [@MaxCods](https://github.com/MaxCods)

