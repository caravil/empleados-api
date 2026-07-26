# Employee API

## Description

REST API developed with Spring Boot for employee management.

The application currently supports employee creation and retrieval through RESTful endpoints. The project follows a layered architecture based on Controller, Service, Repository, DTO, Entity, Mapper, Validator, and Exception components.

---

## Technologies

* Java 17
* Spring Boot 3.5.16
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Swagger / OpenAPI

---

## Requirements

* Java 17 or higher
* Maven 3.9 or higher
* MySQL 8 or higher

---

## Configuration

### 1. Create the database

```sql
CREATE DATABASE parameta_db;
```

### 2. Configure database credentials

Update the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parameta_db
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
```

---

## Running the Project

### Clone the repository

```bash
git clone <repository-url>
cd empleados-api
```

### Compile and run

#### Linux / macOS

```bash
./mvnw clean install
./mvnw spring-boot:run
```

#### Windows

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

---

## API Documentation

### Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI JSON

```text
http://localhost:8080/v3/api-docs
```

---

# Available Endpoints

| Method | Endpoint              | Description                | Status |
| ------ | --------------------- | -------------------------- | :----: |
| POST   | `/api/empleados`      | Create a new employee      |    ✅   |
| GET    | `/api/empleados`      | Retrieve all employees     |    ✅   |
| GET    | `/api/empleados/{id}` | Retrieve an employee by ID |    ✅   |
| PUT    | `/api/empleados/{id}` | Update an employee         |   🚧   |
| DELETE | `/api/empleados/{id}` | Delete an employee         |   🚧   |

---

# Create Employee

**POST**

```text
/api/empleados
```

### Request

```json
{
  "name": "Carlos",
  "lastName": "Sanchez",
  "documentType": "CC",
  "documentNumber": "1234567890",
  "dateOfBirth": "1998-05-10",
  "linkingDate": "2024-01-15",
  "jobTitle": "Software Engineer",
  "salary": 3500000
}
```

### Response

**HTTP 201 Created**

```json
{
  "id": 1,
  "message": "Employee created successfully"
}
```

---

# Get All Employees

**GET**

```text
/api/empleados
```

### Response

**HTTP 200 OK**

```json
[
  {
    "id": 1,
    "name": "Carlos",
    "lastName": "Sanchez",
    "jobTitle": "Software Engineer"
  }
]
```

---

# Get Employee by ID

**GET**

```text
/api/empleados/{id}
```

### Example

```text
/api/empleados/1
```

### Response

**HTTP 200 OK**

```json
{
  "id": 1,
  "name": "Carlos",
  "lastName": "Sanchez",
  "documentType": "CC",
  "documentNumber": "1234567890",
  "dateOfBirth": "1998-05-10",
  "linkingDate": "2024-01-15",
  "jobTitle": "Software Engineer",
  "salary": 3500000
}
```

---

## Project Structure

```text
src/main/java/com/parameta
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── mapper
├── repository
├── service
│   └── impl
├── util
└── validator
```

The application follows a layered architecture, separating responsibilities as follows:

* **Controller:** Exposes REST endpoints and handles HTTP requests.
* **Service:** Contains business logic and application use cases.
* **Repository:** Provides data access through Spring Data JPA.
* **DTO:** Defines request and response objects.
* **Entity:** Represents the persisted database entities.
* **Mapper:** Converts entities into DTOs and vice versa.
* **Validator:** Contains business validation rules.
* **Exception:** Centralizes exception handling and error responses.
* **Config:** Contains application configuration, including Swagger/OpenAPI settings.

---

## Error Handling

The API validates:

* Required fields.
* Positive salary values.
* Valid employee age.
* Valid birth date.
* Valid employment linking date.
* Duplicate document numbers.
* Employee existence when querying by ID.

### Validation Error

**HTTP 400 Bad Request**

```json
{
  "timestamp": "2026-06-26T14:30:15",
  "status": 400,
  "mensaje": "Error de validación",
  "errores": {
    "name": "Los nombres son obligatorios"
  }
}
```

### Business Error

**HTTP 400 Bad Request**

```json
{
  "timestamp": "2026-06-26T14:30:15",
  "status": 400,
  "mensaje": "Ya existe un empleado con ese número de documento.",
  "errores": null
}
```

### Employee Not Found

**HTTP 404 Not Found**

```json
{
  "timestamp": "2026-06-26T17:12:19",
  "status": 404,
  "mensaje": "Employee not found with id: 999",
  "errores": null
}
```
