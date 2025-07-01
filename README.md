## Employee Management System

A robust, scalable RESTful web application for managing employee records, built using **Java 21**, **Spring Boot 3.5.3**, and **MongoDB**.

This project supports full CRUD operations, includes validation, structured exception handling, and unit testing for the service layer.

---

##  Tech Stack

| Technology         | Version / Details         |
|--------------------|---------------------------|
| Java               | 21                        |
| Spring Boot        | 3.5.3                     |
| MongoDB            | NoSQL database (local)    |
| Maven              | Build tool                |
| JUnit & Mockito    | Unit testing              |
| Lombok             | Reduces boilerplate code  |
| Jakarta Validation | Input field validation    |

---

##  Features

-  Add, update, delete, and fetch employee records
-  Field validation using `@NotBlank`, `@Min`, etc.
-  Custom exceptions:
- `ResourceNotFoundException`
- `ResourceAlreadyExistException`
-  MongoDB integration for persistence
-  Unit tests for service layer (e.g., save, update, get)
-  Safe update flow with duplicate checks

---

##  Project Structure

EmployeeMngRepo/
├── Controller/ # REST controllers
├── Service/ # Business logic layer
├── Model/ # Employee entity
├── Repository/ # MongoDB interaction
├── exceptions/ # Custom exception classes
├── test/ # Unit tests for service layer
└── application.properties # MongoDB config


---

##  API Endpoints

| Method | Endpoint             | Description                  |
|--------|----------------------|------------------------------|
| GET    | `/api/employee/`     | Fetch all employees          |
| GET    | `/api/employee/{id}` | Fetch employee by ID         |
| POST   | `/api/employee/`     | Add a new employee           |
| PUT    | `/api/employee/{id}` | Update existing employee     |
| DELETE | `/api/employee/{id}` | Delete employee by ID        |

---

##  Setup Instructions

### Prerequisites

- Java 21 installed
- Maven installed
- MongoDB running (local or cloud)

### Clone and Run

* git clone https://github.com/HimaVarsha01/EmployeeMngRepo.git
* cd EmployeeMngRepo
* mvn clean install
* mvn spring-boot:run

## MongoDB Configuration
In src/main/resources/application.properties, set your MongoDB URI:

# properties

spring.data.mongodb.uri=mongodb://localhost:27017/employeedb

Replace localhost or employeedb with your actual DB connection if needed.

## Unit Testing
Unit tests for the Service Layer are written using JUnit and Mockito.

To run tests:
 mvn test

## Tests include mock-based validation for:

* Saving new employees

* Handling duplicate entries

* Handling resource not found exceptions

* Updating existing employee details

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Author
HimaVarsha Modugula
GitHub: @HimaVarsha01


---
