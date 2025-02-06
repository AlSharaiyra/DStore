# 🛒 E-Commerce Backend

This is a fully functional **E-Commerce Backend REST API** built using **Java Spring Boot**. The backend provides endpoints for managing users, products, orders, carts, payments, and more. It follows RESTful principles and includes essential features like authentication, authorization, and data validation.

## 🚀 Features
- **User Management** (Registration, Login, JWT Authentication[TODO])
- **Product Management** (CRUD operations, Categories, Filtering, Sorting, Pagination[FIX])
- **Cart & Orders** (Add to cart, Checkout, Order tracking)
- **Payment Processing**
- **Security** (Spring Security, JWT-based authentication)
- **Database** (H2 for development, support for MySQL/PostgreSQL in production)

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot (Spring Security, Spring Data JPA, Spring Validation)
- **Database:** H2 (Dev), MySQL/PostgreSQL (Prod)
- **Authentication:** JWT, Spring Security
- **Build Tool:** Maven
- **Testing:** JUnit, Mockito
- **API Documentation:** Swagger/OpenAPI


## 📂 Project Structure
```
📦 ecommerce-backend
├── 📂 src/main/java/com/digitinary/DStore
│   ├── 📂 controller   # REST API Controllers
│   ├── 📂 infra        # Infrastructure Layer
│   │   ├── 📂 exception  # Exception Handling
│   │   ├── 📂 security   # Security Configurations
│   │   │   ├── 📂 password  # Password Management
│   ├── 📂 model        # Entity Models
│   │   ├── 📂 enums    # Enumerations
│   │   ├── 📂 mapper   # Model Mappers
│   │   ├── 📂 request  # Request DTOs
│   │   ├── 📂 response # Response DTOs
│   ├── 📂 repository   # Database Repositories
│   │   ├── 📂 entity   # Entity Definitions
│   │   ├── 📂 repo     # Repository Interfaces
│   ├── 📂 service      # Business Logic Layer
├── 📂 src/main/resources
│   ├── application.yml  # App Configuration
├── 📜 pom.xml  # Maven Dependencies
└── 📜 README.md  # Project Documentation
```

## 🔧 Setup & Installation
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-username/ecommerce-backend.git
cd ecommerce-backend
```

### 2️⃣ Configure the Database
Update `application.yml` for **MySQL/PostgreSQL**:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce_db
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3️⃣ Build & Run the Project
```sh
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Access the API
- API Base URL: `http://localhost:8090/api/v1`
- Swagger UI: `http://localhost:8090/swagger-ui.html`

## 🔑 Authentication
### Register a New User
```sh
POST /api/v1/auth/register
{
  "username": "walid",
  "email": "walid@example.com",
  "password": "securepass"
}
```

### Login & Get JWT Token [TODO]
```sh
POST /api/v1/auth/login
{
  "email": "user@example.com",
  "password": "securepass"
}
```
Response:
```json
{
  "token": "eyJhbGciOiJI..."
}
```
Use the token in requests:
```http
Authorization: Bearer <your_token>
```

## 🤝 Contributing
Feel free to open issues and submit pull requests. Contributions are welcome!

---
💡 **Developed by Walid Sharaiyra**
