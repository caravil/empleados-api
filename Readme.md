# Employee API

## Description

REST API developed with Spring Boot for employee management.

The application currently supports employee creation, retrieval, and update through RESTful endpoints. The project follows a layered architecture based on Controller, Service, Repository, DTO, Entity, Mapper, Validator, and Exception components.

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
* JUnit 5, Mockito, MockMvc (testing)

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

### 2. Configure environment variables

Database credentials are **not** stored in `application.properties`. Instead, create a `.env` file in the project root (this file is git-ignored and must never be committed):

```
DB_USERNAME=your_mysql_user
DB_PASSWORD=your_mysql_password
```

`application.properties` reads these through placeholders:

```properties
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

**Running from the command line:** if your shell doesn't load `.env` automatically, export the variables before running:

```bash
export DB_USERNAME=your_mysql_user
export DB_PASSWORD=your_mysql_password
./mvnw spring-boot:run
```

**Running from VS Code:** add an `envFile` reference in `.vscode/launch.json`:

```jsonc
{
    "configurations": [
        {
            "type": "java",
            "name": "Spring Boot-employeeApiApplication<empleados-api>",
            "request": "launch",
            "mainClass": "com.parameta.employeeApiApplication",
            "envFile": "${workspaceFolder}/.env"
        }
    ]
}
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

## Testing

The project includes unit and integration tests covering business validation, service logic, and the web layer.

```bash
./mvnw test
```

Coverage includes:

* Business validation rules (`EmployeeValidator`)
* Age and employment duration calculations (`EmployeeCalculator`)
* Entity-DTO mapping (`EmployeeMapper`)
* Service layer with mocked repository (`EmployeeServiceImpl`)
* Controller layer with `MockMvc` (`EmployeeController`)

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
| ------ | --------------------- | --------------------------- | :----: |
| POST   | `/api/empleados`      | Create a new employee       |    ✅   |
| GET    | `/api/empleados`      | Retrieve all employees      |    ✅   |
| GET    | `/api/empleados/{id}` | Retrieve an employee by ID  |    ✅   |
| PUT    | `/api/empleados/{id}` | Update an employee          |    ✅   |
| DELETE | `/api/empleados/{id}` | Delete an employee          |   🚧   |

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

### Data Integrity Conflict

**HTTP 409 Conflict**

```json
{
  "timestamp": "2026-09-12T16:30:15",
  "status": 409,
  "mensaje": "El registro entra en conflicto con datos existentes.",
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