# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests


# Runtime stage
FROM eclipse-temurin:21

ARG APP_VERSION=1.0.0

WORKDIR /app

COPY --from=build /build/target/customers-*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]