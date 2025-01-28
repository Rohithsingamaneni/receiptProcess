
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw clean package -DskipTests
RUN ls -l target # Debug: Check the target directory contents


FROM openjdk:21-jdk-slim
WORKDIR /app
# Use a wildcard in case the JAR file name has a version or other differences
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
