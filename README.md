# Product API – Spring Boot Backend Assignment

This project is a **Spring Boot REST API** developed as part of a backend assignment.  
It demonstrates **clean architecture**, **JWT-based authentication**, **role-based authorization**, **MySQL integration**, and **Dockerized deployment**.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 4.x
- Spring Security 7 (JWT Authentication)
- Spring Data JPA (Hibernate)
- MySQL 8
- Maven
- Docker & Docker Compose
- SpringDoc OpenAPI (Swagger)

---

## 📐 Architecture Overview

# Product API – Spring Boot Backend Assignment

This project is a **Spring Boot REST API** developed as part of a backend assignment.  
It demonstrates **clean architecture**, **JWT-based authentication**, **role-based authorization**, **MySQL integration**, and **Dockerized deployment**.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 4.x
- Spring Security 7 (JWT Authentication)
- Spring Data JPA (Hibernate)
- MySQL 8
- Maven
- Docker & Docker Compose
- SpringDoc OpenAPI (Swagger)

---

## 📐 Architecture Overview

Controller → Service → Repository → Database
│
└── Security (JWT, Roles, Filters)

### Key Modules
- **Controller Layer** – REST APIs
- **Service Layer** – Business logic
- **Repository Layer** – JPA repositories
- **Security Layer** – JWT, roles, authentication
- **Config Layer** – Security & application configuration

---

## 🔐 Security Design

- Stateless authentication using **JWT**
- Passwords encrypted using **BCrypt**
- Role-based authorization (e.g. ADMIN, USER)
- Spring Security filter chain (no sessions)

### Public Endpoints
- `/api/auth/**`
- `/swagger-ui.html`
- `/v3/api-docs/**`

All other endpoints require authentication.

---

## 🚀 Running the Application (Local – Without Docker)

### Prerequisites
- Java 21
- Maven
- MySQL running locally

### Database Setup
Create database in MySQL:
```sql
CREATE DATABASE product_db;

Update application.yml if needed:
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/product_db
    username: root
    password: root

Run Application

mvn clean spring-boot:run

Swagger UI:

http://localhost:8080/swagger-ui.html

🐳 Running the Application (Docker – Recommended)
Prerequisites

Docker Desktop installed and running

Build JAR
mvn clean package -DskipTests

Start Containers
docker compose up --build

Stop Containers
docker compose down

Access

API: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html

📂 Project Structure

product-api
 ├── src/main/java
 │   ├── controller
 │   ├── service
 │   ├── repository
 │   ├── entity
 │   ├── security
 │   └── config
 ├── src/main/resources
 │   └── application.yml
 ├── Dockerfile
 ├── docker-compose.yml
 ├── pom.xml
 └── README.md

✅ Features Implemented

RESTful APIs
JWT Authentication & Authorization
Role management
MySQL persistence
Swagger documentation
Dockerized deployment
Clean layered architecture

👤 Author

Vishwajeet More
Java Backend Developer