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
## Swagger UI

http://localhost:8081/swagger-ui/index.html

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/products | Get all products |
| GET | /api/v1/products/{id} | Get product by ID |
| POST | /api/v1/products | Create product |
| PUT | /api/v1/products/{id} | Update product |
| DELETE | /api/v1/products/{id} | Delete product |

---

## Setup

```bash
git clone <repo-url>
cd product-service
mvn clean install
mvn spring-boot:run