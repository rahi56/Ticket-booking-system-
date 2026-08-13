# 🎟️ Ticket Booking System

A backend-based **Ticket Booking System** built with **Spring Boot** that provides RESTful APIs for managing users, events, tickets, bookings, and authentication.

The system is designed to provide a secure and scalable platform where users can browse available events, book tickets, and manage their bookings, while administrators can manage events, tickets, and users.

---

## 🚀 Features

### 👤 User Management

* User registration
* User login
* JWT-based authentication
* Role-based authorization
* User profile management

### 🎫 Ticket & Event Management

* Create events
* Update event information
* Delete events
* View available events
* View ticket availability
* Manage ticket prices and quantities

### 🎟️ Booking System

* Book tickets
* Check ticket availability
* View booking history
* Cancel bookings
* Prevent overbooking

### 🔐 Security

* Spring Security
* JWT authentication
* Password encryption using BCrypt
* Role-based access control
* Protected REST APIs

### 🛠️ Admin Features

* Manage users
* Manage events
* Manage tickets
* Manage bookings
* Monitor booking information

---

## 🧰 Technologies Used

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java            | Programming Language           |
| Spring Boot     | Backend Framework              |
| Spring Security | Authentication & Authorization |
| JWT             | Token-based Authentication     |
| Spring Data JPA | Database Access                |
| Hibernate       | ORM                            |
| MySQL           | Database                       |
| Maven           | Dependency Management          |
| REST API        | Client-Server Communication    |
| Git & GitHub    | Version Control                |

---

## 🏗️ System Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
REST Controller
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

### Main Layers

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

* **Controller** — Handles HTTP requests and responses.
* **Service** — Contains business logic.
* **Repository** — Communicates with the database using Spring Data JPA.
* **Entity** — Represents database tables.
* **DTO** — Transfers data between the client and server.
* **Security** — Handles JWT authentication and authorization.

---

## 📂 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com.example.ticketbooking/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── entity/
    │       ├── dto/
    │       ├── security/
    │       ├── exception/
    │       └── TicketBookingApplication.java
    │
    └── resources/
        ├── application.properties
        └── static/
```

> The package structure may vary depending on the implementation.

---

## 🗄️ Database

The application uses **MySQL** as the relational database.

### Main Entities

```text
User
 │
 ├── Booking
 │      │
 │      └── Ticket
 │
 └── Role

Event
 │
 └── Ticket
```

Example database relationships:

```text
User 1 ──────── * Booking

Event 1 ─────── * Ticket

Booking * ────── 1 Ticket
```

---

## 🔐 Authentication Flow

The application uses JWT for authentication.

```text
User
 │
 │ Login
 ▼
Authentication Controller
 │
 ▼
Spring Security
 │
 ▼
JWT Token Generated
 │
 ▼
Client
 │
 │ Authorization: Bearer <token>
 ▼
Protected API
```

After successful login, the server generates a JWT token.

The client then sends the token with protected requests:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint             | Description         |
| ------ | -------------------- | ------------------- |
| POST   | `/api/auth/register` | Register a new user |
| POST   | `/api/auth/login`    | Login user          |

### Users

| Method | Endpoint          | Description |
| ------ | ----------------- | ----------- |
| GET    | `/api/users/{id}` | Get user    |
| PUT    | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Events

| Method | Endpoint           | Description     |
| ------ | ------------------ | --------------- |
| GET    | `/api/events`      | Get all events  |
| GET    | `/api/events/{id}` | Get event by ID |
| POST   | `/api/events`      | Create event    |
| PUT    | `/api/events/{id}` | Update event    |
| DELETE | `/api/events/{id}` | Delete event    |

### Tickets

| Method | Endpoint            | Description           |
| ------ | ------------------- | --------------------- |
| GET    | `/api/tickets`      | Get available tickets |
| GET    | `/api/tickets/{id}` | Get ticket            |
| POST   | `/api/tickets`      | Create ticket         |
| PUT    | `/api/tickets/{id}` | Update ticket         |

### Bookings

| Method | Endpoint                      | Description         |
| ------ | ----------------------------- | ------------------- |
| POST   | `/api/bookings`               | Create booking      |
| GET    | `/api/bookings/{id}`          | Get booking         |
| GET    | `/api/bookings/user/{userId}` | Get user's bookings |
| DELETE | `/api/bookings/{id}`          | Cancel booking      |

> Update these endpoints to match the actual controllers in your project.

---

## ⚙️ Requirements

Before running the project, make sure you have:

* Java 17+
* Maven
* MySQL
* Git
* IntelliJ IDEA / VS Code / Eclipse

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## 🗄️ MySQL Configuration

Create a database:

```sql
CREATE DATABASE ticket_booking;
```

Then configure your database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_booking
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> Do not commit your real database password or JWT secret to GitHub.

For production, use environment variables or a `.env`/secret-management solution.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/ticket-booking-system.git
```

### 2. Enter the project directory

```bash
cd ticket-booking-system
```

### 3. Configure MySQL

Create the database and update your database credentials in `application.properties`.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

---

## 🧪 API Testing

You can test the REST APIs using:

* Postman
* Insomnia
* cURL
* Swagger UI

Example login request:

```http
POST /api/auth/login
Content-Type: application/json
```

Request:

```json
{
    "email": "user@example.com",
    "password": "password123"
}
```

Example response:

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 📸 Screenshots

Add screenshots of your application/API testing here.

Example:

```text
docs/
├── login.png
├── registration.png
├── events.png
├── booking.png
└── database.png
```

Then add them to the README:

```markdown
![Login](docs/login.png)

![Event List](docs/events.png)

![Booking](docs/booking.png)
```

---

## 🔒 Security Considerations

The application implements several security mechanisms:

* JWT-based authentication
* BCrypt password hashing
* Role-based authorization
* Protected REST endpoints
* Input validation
* Exception handling

Sensitive configuration such as database credentials and JWT secrets should be stored outside the source code.

---

## 🔮 Future Improvements

Possible future improvements include:

* Online payment integration
* Email booking confirmation
* QR-code based tickets
* Ticket PDF generation
* Redis caching
* Real-time seat availability
* Docker deployment
* CI/CD pipeline
* Swagger/OpenAPI documentation
* Cloud deployment
* Advanced admin dashboard
* Booking notifications

---

## 🎯 Learning Objectives

This project demonstrates practical experience with:

* Spring Boot application development
* REST API design
* Spring Security
* JWT authentication
* Role-based authorization
* Spring Data JPA
* Hibernate ORM
* MySQL database design
* Entity relationships
* Exception handling
* Backend architecture
* API testing

---

## 👨‍💻 Author

**Abdur Rahim**

Computer Science & Engineering Student

* GitHub: `https://github.com/rahi56`
* Codeforces: `https://codeforces.com/profile/rahim02ar`

---

## ⭐ Contributing

Contributions, issues, and feature requests are welcome.

If you find this project useful, consider giving it a ⭐ on GitHub.

---

## 📄 License

This project is available for educational and personal use.
