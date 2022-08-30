FROM maven:3.6.3 AS build-stage

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:18-alpine AS runtime

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build-stage /usr/src/app/target/*.jar /usr/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/app/app.jar"]