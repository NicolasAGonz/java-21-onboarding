# Stage 1: Build the application
FROM maven:3.9.8-amazoncorretto-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project and build the JAR file
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build output (JAR file) to the container
COPY --from=build /app/target/MS-Cuentas-0.0.1-SNAPSHOT.jar MS-Cuentas-0.0.1-SNAPSHOT.jar

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","MS-Cuentas-0.0.1-SNAPSHOT.jar"]
