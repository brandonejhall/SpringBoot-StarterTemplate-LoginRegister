

# Swift Recruit - Monolithic Architecture

This repository contains the source code for a Recruitment Platform initially built as a monolithic application using Spring Boot. The platform is designed to handle various functionalities related to user management, job postings, applications, interview scheduling, notifications, feedback, evaluations, analytics, and file storage.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Endpoints](#endpoints)
- [Getting Started](#getting-started)
- [Building and Running](#building-and-running)
- [Configuration](#configuration)
- [Testing](#testing)
- [Future Plans](#future-plans)

## Project Structure

The project is organized into packages representing different services:

```
src/main/java/com/example/recruitment
|-- analytics
|-- application
|-- config
|-- feedback
|-- filestorage
|-- interview
|-- jobposting
|-- notification
|-- usermanagement
```

Each package contains the necessary controllers, services, repositories, and entities.

## Features

- **User Management**: Register, login, logout, manage profiles, reset passwords, and change user roles.
- **Job Posting**: Create, update, delete, and retrieve job postings.
- **Application Management**: Submit, update, delete, and retrieve job applications.
- **Interview Scheduling**: Schedule interviews, manage interview slots.
- **Notification**: Send email and SMS notifications.
- **Feedback and Evaluation**: Submit and manage feedback and evaluations.
- **Analytics**: Retrieve analytics data and generate reports.
- **File Storage**: Upload, download, and delete files.

## Endpoints

### User Management Service

- `POST /user/register`: Register a new user
- `POST /user/login`: Log in an existing user
- `POST /user/logout`: Log out an existing user
- `GET /user/profile`: Get user profile information
- `PUT /user/profile`: Update user profile information
- `POST /user/reset-password`: Reset user password
- `POST /user/change-role`: Change user role

### Job Posting Service

- `POST /jobs`: Create a new job posting
- `GET /jobs`: Get a list of job postings
- `GET /jobs/{id}`: Get a specific job posting by ID
- `PUT /jobs/{id}`: Update a job posting by ID
- `DELETE /jobs/{id}`: Delete a job posting by ID
- `GET /jobs/{id}/link`: Get the application link for a job posting

### Application Service

- `POST /applications`: Submit a new application
- `GET /applications`: Get a list of applications
- `GET /applications/{id}`: Get a specific application by ID
- `PUT /applications/{id}`: Update an application by ID
- `DELETE /applications/{id}`: Delete an application by ID

### Interview Scheduling Service

- `POST /schedules`: Create a new interview schedule
- `GET /schedules`: Get a list of interview schedules
- `GET /schedules/{id}`: Get a specific interview schedule by ID
- `PUT /schedules/{id}`: Update an interview schedule by ID
- `DELETE /schedules/{id}`: Delete an interview schedule by ID
- `POST /schedules/{id}/slots`: Add interview slots to a schedule
- `GET /schedules/{id}/slots`: Get available interview slots for a schedule

### Notification Service

- `POST /notifications/email`: Send an email notification
- `POST /notifications/sms`: Send an SMS notification
- `GET /notifications/templates`: Get a list of notification templates
- `PUT /notifications/templates/{id}`: Update a notification template by ID
- `DELETE /notifications/templates/{id}`: Delete a notification template by ID

### Feedback and Evaluation Service

- `POST /feedback`: Submit feedback for an interview
- `GET /feedback`: Get a list of feedback submissions
- `GET /feedback/{id}`: Get a specific feedback submission by ID
- `PUT /feedback/{id}`: Update a feedback submission by ID
- `DELETE /feedback/{id}`: Delete a feedback submission by ID
- `POST /evaluations`: Create a new evaluation for an application
- `GET /evaluations`: Get a list of evaluations
- `GET /evaluations/{id}`: Get a specific evaluation by ID
- `PUT /evaluations/{id}`: Update an evaluation by ID
- `DELETE /evaluations/{id}`: Delete an evaluation by ID

### Analytics Service

- `GET /analytics`: Get general analytics information
- `GET /analytics/metrics`: Get specific analytics metrics
- `GET /analytics/reports`: Get analytics reports
- `POST /analytics/reports`: Generate a new analytics report

### File Storage Service

- `POST /files`: Upload a file
- `GET /files/{id}`: Download a file by ID
- `DELETE /files/{id}`: Delete a file by ID

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL (or any preferred database)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/recruitment-platform.git
    cd recruitment-platform
    ```

2. Configure the database settings in `src/main/resources/application.yml`:

    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/recruitment
        username: yourusername
        password: yourpassword
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```

3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

## Building and Running

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

## Configuration

Configure application-specific settings in `src/main/resources/application.yml`.

## Testing

Run unit and integration tests using Maven:

```bash
mvn test
```

## Future Plans

- Transition to a microservices architecture.
- Implement service discovery using Eureka.
- Introduce API gateway using Spring Cloud Gateway.
- Split the database into separate databases for each microservice.
- Enhance security measures (OAuth2, JWT, etc.).

---

