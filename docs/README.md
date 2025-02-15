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

## 2️⃣ Build and Start the Docker Container
```bash
docker-compose up --build
```

### 🛠 Application Access
- Once started, the application will be available at:  
  **`http://localhost:8080/graphql`**
- Configuration (e.g., database credentials) can be adjusted in `docker-compose.yml`.

---

## 🧪 Viewing Test Results
- **Test results are generated during the build process** and stored inside the container at:  
  `/app/surefire-reports`
- **You cannot rerun tests inside the container** because they are executed as part of the build process (`mvn verify`).
- To access the reports:
  `docker exec -it eventmanager ls /app/surefire-reports`

---

## 🔄 Rerunning Tests
If you want to **rerun tests outside of an IDE**, you must **fully rebuild the container**, because tests run **during the build phase**:
```bash
docker-compose build --no-cache
docker-compose up --force-recreate --remove-orphans
```
### 🛠 Why is this necessary?
- **Tests only run during `mvn verify`**, which happens at **build time**.
- **Docker caches previous builds**, meaning **tests won't rerun unless you force a rebuild**.
- **`--no-cache` ensures fresh execution**, preventing Docker from reusing old layers.
- **`--force-recreate` starts a new instance**, ensuring that test results are freshly generated.

---

## 🔴 Stopping the Application
If you need to stop the application:

```bash
docker-compose down
```
This will **stop and remove** all running containers **but keep the database volumes**.

### 🧹 Full Cleanup (Including Database)
If you want to **completely reset** the environment, including database data:

```bash
docker-compose down -v --remove-orphans
```
---

## ▶️ Restarting the Application After Stopping
If you've previously stopped the container and want to restart it:

```bash
docker-compose up
```

If you made changes and need to **rebuild before restarting**, use:

```bash
docker-compose up --build
```
---

## Contributors
- [@Daskyy](https://github.com/Daskyy)
- [@SebasN12](https://github.com/SebasN12)
- [@mvx-320](https://github.com/mvx-320)
- [@MaxCods](https://github.com/MaxCods)

