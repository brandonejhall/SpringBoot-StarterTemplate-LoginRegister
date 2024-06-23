# SpringBoot-StarterTemplate-LoginRegister

A Spring Boot starter template for implementing a basic login and registration system. This project aims to provide a simple, easy-to-use starting point for developing secure user authentication and management functionalities in a Spring Boot application.

## Features

- User registration with validation
- User login with JWT authentication
- Password encryption using BCrypt
- Role-based access control
- Secure REST API endpoints
- Integration with H2 database (can be switched to other databases)
- Comprehensive error handling
- Basic user profile management

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (JSON Web Tokens)
- Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SpringBoot-StarterTemplate-LoginRegister.git
   cd SpringBoot-StarterTemplate-LoginRegister
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8080`

## Usage

### Register a new user

Send a POST request to `/api/auth/register` with the following JSON payload:
```json
{
    "firstName": "FirstName",
    "lastName": "LastName",
    "password": "yourPassword",
    "email": "yourEmail@example.com"
}
```

### Login

Send a POST request to `/api/auth/login` with the following JSON payload:
```json
{
    "email": "Email",
    "password": "yourPassword"
}
```

The response will contain a JWT token which should be used to authenticate subsequent requests.

### Accessing secured endpoints

Include the JWT token stored in the JWT_TOKEN cookie in your requests. The application will automatically read the token from the cookie to authenticate the user.

## Configuration

The application properties can be configured in `src/main/resources/application.properties`. By default, it uses an H2 in-memory database. To switch to another database, update the relevant properties.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or feedback, please contact [your email](mailto:your.email@example.com).

---

Enjoy building with Spring Boot!
