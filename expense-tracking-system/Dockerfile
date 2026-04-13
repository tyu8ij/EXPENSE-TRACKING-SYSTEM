# Use official Java runtime (Eclipse Temurin - maintained and available)
FROM eclipse-temurin:11-jre-alpine

# Set working directory in container
WORKDIR /app

# Copy the JAR file
COPY target/expense-tracking-system-1.0.0.jar app.jar

# Expose port (if needed for API)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
