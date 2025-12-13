# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper & pom
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (cached)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/ecommerce-backend-0.0.1-SNAPSHOT.jar"]
