# Prueba Técnica Parameta - API de Empleados

## Descripción

API REST desarrollada con Spring Boot para el registro de empleados y el cálculo de información derivada, como:

- Edad actual del empleado.
- Tiempo de vinculación con la empresa.

La aplicación expone un endpoint para crear empleados y devuelve la información calculada en la respuesta.


## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.5.16
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger / OpenAPI


## Requisitos

- Java 17 o superior
- Maven 3.9 o superior
- MySQL 8 o superior

## Configuración

### 1. Crear la base de datos

```sql
CREATE DATABASE parameta_db;
```

### 2. Configurar las credenciales

Modificar el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parameta_db
spring.datasource.username= tu usuario de mysql
spring.datasource.password= tu contraseña de mysql
```

## Ejecución del proyecto

### Clonar el proyecto

```bash
git clone <url-del-repositorio>
cd empleados-api
```

### Compilar y ejecutar

```bash
./mvnw clean install
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

## Documentación de la API

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

## Endpoint Disponible

### Crear Empleado

**POST**

```
http://localhost:8080/api/empleados
```

### Ejemplo de Request

```json
{
  "nombres": "Carlos Andrés",
  "apellidos": "Sánchez Avilés",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "fechaNacimiento": "1998-05-10",
  "fechaVinculacion": "2024-01-15",
  "cargo": "Ingeniero de Sistemas",
  "salario": 3500000
}
```

### Ejemplo de Response

```json
{
  "id": 1,
  "nombres": "Carlos Andrés",
  "apellidos": "Sánchez Avilés",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "fechaNacimiento": "1998-05-10",
  "fechaVinculacion": "2024-01-15",
  "cargo": "Ingeniero de Sistemas",
  "salario": 3500000,
  "edadActual": "28 años, 1 mes y 16 días",
  "tiempoVinculacion": "2 años, 5 meses y 11 días"
}
```

## Estructura del Proyecto

```
src/main/java/com/parameta
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
└── service
```
La aplicación fue desarrollada siguiendo una arquitectura por capas (N-Tier), separando las responsabilidades de la siguiente manera:

- **Controller:** expone los endpoints REST y recibe las solicitudes HTTP.
- **Service:** contiene la lógica de negocio y el procesamiento de la información.
- **Repository:** realiza el acceso a datos mediante Spring Data JPA.
- **DTO:** define los objetos de entrada y salida de la API.
- **Entity:** representa las entidades persistidas en la base de datos.
- **Exception:** centraliza el manejo de excepciones y errores de validación.
- **Config:** contiene las configuraciones de la aplicación, como OpenAPI/Swagger.


## Manejo de Errores

La API realiza validaciones sobre:

- Campos obligatorios.
- Salario mayor a cero.
- Formato de los datos enviados.

Las respuestas de error tienen la siguiente estructura:

```json
{
  "timestamp": "2026-06-26T14:30:15",
  "status": 400,
  "mensaje": "Error de validación",
  "errores": {
    "nombres": "Los nombres son obligatorios"
  }
}
```