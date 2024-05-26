FROM openjdk:17-jdk-slim
ADD /build/libs/*.jar app.jar
COPY src/main/resources/templates /templates
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
