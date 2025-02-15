# Use Maven as both build and runtime environment
FROM maven:3.9.9-eclipse-temurin-23

WORKDIR /app

# Copy project files
COPY pom.xml ./
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Find the correct JAR name and rename it to app.jar for consistency
RUN mkdir -p target && mv target/*.jar target/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/app.jar"]
