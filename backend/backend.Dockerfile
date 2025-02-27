# Use the official Java 21 image as a base
FROM eclipse-temurin:21-jdk AS build
WORKDIR /backend

# Copy Gradle wrapper and settings first (to cache dependencies)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure Gradle wrapper has execute permissions
RUN chmod +x gradlew

# Copy the source code early (for live reload support)
COPY src src

# Run the Spring Boot application using Gradle (DevTools enabled)
CMD ["sh", "-c", "./gradlew bootRun --no-daemon"]