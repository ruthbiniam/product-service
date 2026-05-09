# product-service

A RESTful Product Service built with Spring Boot 3.

## Dependecies

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## Run Application

```bash
mvn spring-boot:run
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /products | Get all products |
| GET | /products/{id} | Get product by ID |
| POST | /products | Create product |
| GET | /health | Health check |

## Sample JSON

```json
{
  "name": "Laptop",
  "price": 1200
}
```
