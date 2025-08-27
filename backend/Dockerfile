# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the pom.xml file to leverage Docker cache layers
COPY pom.xml .

# Download dependencies (this will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Expose the port that Spring Boot will run on
EXPOSE 8080

# Create a startup script
RUN echo '#!/bin/bash\necho "🐾 Starting Pet Paradise Backend Server..."\necho "📦 Dependencies: Java 17 + Maven + H2 Database"\necho "🌐 Server will be available at: http://localhost:8080/api/v1"\necho "📚 API Documentation: http://localhost:8080/api/v1/swagger-ui.html"\necho "🗄️ Database Console: http://localhost:8080/api/v1/h2-console"\necho ""\nmvn clean spring-boot:run' > start.sh && chmod +x start.sh

# Set the default command to run the application
CMD ["./start.sh"]
