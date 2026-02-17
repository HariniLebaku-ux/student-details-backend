FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/student-details-backend.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]