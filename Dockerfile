# Build Stage
FROM maven:3.9.9-eclipse-temurin-23 AS builder
WORKDIR /app

COPY pom.xml ./
COPY src ./src

# Run tests and package application
RUN mvn clean verify && mvn package


# Production Stage
FROM eclipse-temurin:23-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/target/surefire-reports /app/surefire-reports

EXPOSE 8080
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
