# PawConnect – Pet Care Network

PawConnect is a pet care community platform designed to connect pet owners, veterinarians, and pet lovers in one place. It provides a space for users to create profiles, share pet-related posts, and connect with the pet community.

## Features

- User registration and login
- JWT-based authentication and authorization
- Role-based user management
- Secure password hashing using BCrypt
- RESTful APIs
- Pet community post creation with image uploads
- MySQL database integration

## User Roles

- PET_OWNER
- PET_LOVER
- VETERINARIAN

## Technologies Used

| Category | Technologies |
|---|---|
| Language | Java |
| Backend Framework | Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL |
| ORM | Spring Data JPA, Hibernate |
| Build Tool | Maven |
| API Testing | Postman |

## Project Structure

```text
PawConnect/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mario/main/
│       └── resources/
│           └── application.properties
├── .gitignore
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Getting Started

### Prerequisites

- Java 17 or compatible version required by the project
- MySQL
- Maven (or the included Maven Wrapper)

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Noorayen/PawConnect.git
   ```

2. Open the project in Spring Tool Suite (STS) or your preferred IDE.

3. Create a MySQL database named `pawconnect`.

4. Configure the following environment variables:

   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
   - `JWT_SECRET`
   - `JWT_EXPIRATION` (optional; defaults to 86400000 milliseconds)

5. Run the application using Spring Tool Suite or the Maven Wrapper:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows:

   ```cmd
   mvnw.cmd spring-boot:run
   ```

The application runs on:

`http://localhost:8081`

## Developer

Developed as a Java Spring Boot backend project to explore secure REST API development, authentication, database integration, and backend application design.
