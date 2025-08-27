# Pet Paradise Backend API

A comprehensive Spring Boot backend application for Pet Paradise - a full-service pet care platform offering grooming, boarding, daycare, pool sessions, and pet products.

## 🚀 Features

### Core Services
- **Pet Grooming** - Full-service grooming, baths, nail trimming, dental cleaning
- **Pet Boarding** - Overnight and extended stay boarding with luxury options
- **Daycare & Play** - Supervised play sessions and socialization
- **Pool & Spa** - Swimming sessions, hydrotherapy, and pool parties
- **Pet Shop** - Food, toys, accessories, and health care products

### API Capabilities
- **RESTful API** with comprehensive CRUD operations
- **Advanced Search** with filtering, pagination, and sorting
- **Data Validation** with comprehensive error handling
- **API Documentation** with Swagger/OpenAPI integration
- **CORS Support** for React frontend integration
- **Database Management** with JPA/Hibernate and MySQL

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Database**: MySQL 8.0+ with JPA/Hibernate
- **Build Tool**: Maven
- **Java Version**: 17
- **Documentation**: Swagger/OpenAPI 3
- **Validation**: Jakarta Bean Validation
- **Mapping**: ModelMapper for DTO conversions

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

## 🔧 Setup & Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd PetParadise-React-Spring-/backend
```

### 2. Database Setup
Create a MySQL database:
```sql
CREATE DATABASE petparadise;
CREATE USER 'petparadise_user'@'localhost' IDENTIFIED BY 'petparadise_password';
GRANT ALL PRIVILEGES ON petparadise.* TO 'petparadise_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configuration
Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/petparadise
spring.datasource.username=petparadise_user
spring.datasource.password=petparadise_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.servlet.context-path=/api/v1
server.port=8080

# CORS Configuration
cors.allowed-origins=http://localhost:3000,http://localhost:5173,http://localhost:5174
```

### 4. Build & Run
```bash
# Install dependencies and build
mvn clean install

# Run the application
mvn spring-boot:run
```

The server will start on `http://localhost:8080/api/v1`

## 📖 API Documentation

### Swagger UI
Access interactive API documentation at:
```
http://localhost:8080/api/v1/swagger-ui.html
```

### Main Endpoints

#### Booking Forms
- `POST /api/v1/Forms` - Create booking
- `GET /api/v1/Forms` - List all bookings
- `GET /api/v1/Forms/{id}` - Get booking by ID
- `PUT /api/v1/Forms/{id}` - Update booking
- `DELETE /api/v1/Forms/{id}` - Delete booking
- `GET /api/v1/Forms/search` - Search bookings
- `PATCH /api/v1/Forms/{id}/status` - Update booking status

#### Products
- `POST /api/v1/products` - Create product
- `GET /api/v1/products` - List all products
- `GET /api/v1/products/{id}` - Get product by ID
- `PUT /api/v1/products/{id}` - Update product
- `DELETE /api/v1/products/{id}` - Delete product
- `GET /api/v1/products/search` - Search products
- `GET /api/v1/products/category/{category}` - Products by category
- `GET /api/v1/products/price-range` - Filter by price range
- `GET /api/v1/products/top-rated` - Top-rated products
- `GET /api/v1/products/best-sellers` - Best-selling products
- `GET /api/v1/products/low-stock` - Low stock products
- `GET /api/v1/products/statistics` - Product statistics

#### Services
- `POST /api/v1/services` - Create service
- `GET /api/v1/services` - List all services
- `GET /api/v1/services/{id}` - Get service by ID
- `PUT /api/v1/services/{id}` - Update service
- `DELETE /api/v1/services/{id}` - Delete service
- `GET /api/v1/services/search` - Search services
- `GET /api/v1/services/type/{serviceType}` - Services by type
- `GET /api/v1/services/active` - Active services only
- `PATCH /api/v1/services/{id}/toggle-status` - Toggle service status
- `GET /api/v1/services/types` - All service types
- `GET /api/v1/services/statistics` - Service statistics

## 🗄️ Database Schema

### Entities

#### BookingForm
- `id` (Long, Primary Key)
- `first_Name` (String, Not Null)
- `last_Name` (String, Not Null)
- `email` (String, Not Null, Valid Email)
- `mobile_No` (String, Not Null)
- `pet_Name` (String, Not Null)
- `pet_Type` (String, Not Null)
- `service_Type` (String, Not Null)
- `booking_Date` (String, Not Null)
- `special_Requests` (String, Optional)
- `status` (BookingStatus Enum)
- `created_At` (LocalDateTime)
- `updated_At` (LocalDateTime)

#### Product
- `id` (Long, Primary Key)
- `name` (String, Not Null)
- `description` (String)
- `price` (BigDecimal, Not Null)
- `originalPrice` (BigDecimal)
- `category` (String, Not Null)
- `brand` (String)
- `stockQuantity` (Integer, Default: 0)
- `imageUrl` (String)
- `isActive` (Boolean, Default: true)
- `rating` (Double, Default: 0.0)
- `reviewCount` (Integer, Default: 0)
- `createdAt` (LocalDateTime)
- `updatedAt` (LocalDateTime)

#### Service
- `id` (Long, Primary Key)
- `name` (String, Not Null)
- `serviceType` (String, Not Null)
- `description` (String)
- `priceRange` (String)
- `duration` (String)
- `imageUrl` (String)
- `isActive` (Boolean, Default: true)
- `createdAt` (LocalDateTime)
- `updatedAt` (LocalDateTime)

## 🧪 Sample Data

The application automatically initializes with sample data:

### Products (12 items)
- Dog & Cat Food (Premium nutrition)
- Toys (Interactive puzzles, catnip mice, rope toys)
- Accessories (Collars, leashes, scratching posts)
- Health & Care (Shampoo, dental chews, nail clippers)

### Services (16 items)
- **Grooming**: Full service, basic bath, nail trimming, dental cleaning
- **Boarding**: Overnight, extended stay, luxury suites
- **Daycare**: Full day, half-day, private sessions
- **Pool**: Swimming, hydrotherapy, pool parties
- **Training**: Basic obedience, advanced training, puppy classes

## 🔍 Advanced Features

### Search & Filtering
- **Text Search**: Search by name, description, or keywords
- **Category Filtering**: Filter products by category
- **Price Range**: Filter products within price ranges
- **Status Filtering**: Filter by active/inactive status
- **Date Filtering**: Filter bookings by date ranges
- **Combined Searches**: Multiple criteria searches

### Pagination & Sorting
- Configurable page sizes
- Sort by any field (name, price, date, etc.)
- Ascending/descending order support

### Statistics & Analytics
- Total counts and active/inactive breakdowns
- Category distributions
- Revenue calculations
- Booking status analytics
- Stock level monitoring

## 🛡️ Error Handling

Comprehensive error handling with:
- Custom exception classes
- Global exception handler
- Validation error responses
- HTTP status code consistency
- Detailed error messages

## 🔧 Configuration

### Environment Variables
Create a `.env` file in the project root:
```env
# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=petparadise
DB_USERNAME=petparadise_user
DB_PASSWORD=petparadise_password

# Server
SERVER_PORT=8080
CONTEXT_PATH=/api/v1

# CORS
ALLOWED_ORIGINS=http://localhost:3000,http://localhost:5173,http://localhost:5174
```

### Production Configuration
For production deployment:
1. Update database configuration for production DB
2. Set `spring.jpa.hibernate.ddl-auto=validate`
3. Configure proper logging levels
4. Set up SSL/HTTPS
5. Configure production CORS origins

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/petparadise-backend-1.0.0.jar
```

### Docker (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/petparadise-backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📊 Monitoring & Health Checks

Access health endpoints:
- `/api/v1/actuator/health` - Application health
- `/api/v1/Forms/health` - Booking service health
- `/api/v1/products/health` - Product service health
- `/api/v1/services/health` - Service management health

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License.

## 📞 Support

For support and questions:
- Email: support@petparadise.com
- Documentation: [API Docs](http://localhost:8080/api/v1/swagger-ui.html)
- Issues: GitHub Issues

---

**Pet Paradise Backend** - Powering the future of pet care services! 🐾
