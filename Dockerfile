# Use the official OpenJDK 20 image as the base image
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files into the image
COPY . .

# Run the Maven clean package command
RUN mvn clean package -DskipTests

# Use the appropriate OpenJDK image for Java 20 runtime
FROM openjdk:17-jdk-slim

# Copy the built jar file from the build stage
COPY --from=build /app/target/user-reg-0.0.1-SNAPSHOT.jar /usr/src/app/user-reg.jar

# Set the working directory
WORKDIR /usr/src/app

# Set the entry point for the Docker container
ENTRYPOINT ["java", "-jar", "user-reg.jar"]