# ShopIt 🛒

**ShopIt** is a robust, production-ready E-commerce Backend API built with **Spring Boot 3** and **Java 21**. It features a secure, scalable architecture designed to handle core e-commerce operations including user management, product cataloging, and order processing.

---

## 🚀 Key Features

*   **Security First**: Custom JWT Authentication & Authorization filter chain (Stateless).
*   **Role-Based Access Control (RBAC)**: Distinct permissions for `ADMIN` (Product management) and `CUSTOMER` (Order placement).
*   **Data Integrity**: Implements "Price Snapshotting" for Order Items to preserve historical pricing accuracy.
*   **Clean Architecture**: Separation of concerns with Framework-Agnostic Service Layer and Custom Exception Handling.
*   **Modern Tech Stack**: Java 21, Spring Boot 3.5.9, Spring Data JPA, Lombok.

---

## 🛠️ Technology Stack

*   **Language**: Java 21
*   **Framework**: Spring Boot 3.5.9 (Web, Security, Data JPA, Validation)
*   **Database**: MySQL 8.0
*   **Build Tool**: Maven
*   **Tools**: Lombok, Docker (Ready)

---

## ⚙️ Installation & Setup

### Prerequisites
*   Java 21 SDK
*   MySQL Server
*   Maven

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/ShopIt.git
cd ShopIt
```

### 2. Configure Environment Variables
This project uses **Environment Variables** for security. You can set them in your IDE or terminal:

| Variable | Description | Example |
| :--- | :--- | :--- |
| `DB_USERNAME` | MySQL Username | `root` |
| `DB_PASSWORD` | MySQL Password | `password123` |
| `JWT_SECRET_KEY` | Secret for Token Signing | `your_super_secret_key_at_least_32_chars` |

### 3. Run the Application
```bash
./mvnw spring-boot:run
```
The server will start on `http://localhost:8080`.

---

## 🔌 API Endpoints

### **Authentication**
*   `POST /auth/register` - Register a new customer
*   `POST /auth/login` - Login and receive JWT Bearer Token

### **Products (Public)**
*   `GET /product/getallproducts` - View catalog
*   `GET /product/getProductById/{id}` - View details

### **Orders (Customer)**
*   `POST /orders` - Place a new order
*   `GET /orders/me` - View my order history

### **Admin Operations**
*   `POST /product/foradmin/addproduct` - Create inventory
*   `PUT /product/foradmin/updateproduct` - Update inventory
*   `PUT /orders/admin/{id}` - Update order status (PENDING -> SHIPPED)

---

## 🏗️ Architecture Highlights

### **Global Exception Handling**
The application uses a `@ControllerAdvice` to map custom business exceptions to standard HTTP responses, ensuring a consistent error structure:
```json
{
  "timestamp": "2024-01-22T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id: 5",
  "path": "/product/5"
}
```

### **Database Design**
*   **Users**: Stores credentials and roles.
*   **Products**: Catalog with active status flags.
*   **Orders**: Loose coupling to Users (ID-reference) for scalability.
*   **OrderItems**: Stores `priceAtPurchase` to lock in historical prices.

---

## 👨‍💻 Author
**AbedProjects**
