FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM amazoncorretto:21-alpine-jdk
COPY --from=build /app/target/vlad-project-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]