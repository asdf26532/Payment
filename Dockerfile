FROM openjdk:17-jdk-slim
COPY target/myapp.jar /app.jar
COPY src/main/resources/templates /templates
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]