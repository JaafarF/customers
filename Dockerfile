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

COPY --from=build /build/target/customers-*.jar /app/

EXPOSE 8080

ENV DB_URL=jdbc:postgresql://postgres-customers-db:5432/customers
ENV JAR_VERSION=${APP_VERSION}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.datasource.url=${DB_URL} customers-${JAR_VERSION}.jar