# Etap budowy aplikacji w Mavenie
FROM maven:3.9.5-amazoncorretto-21 AS builder

WORKDIR /app

# Kopiowanie plików projektu
COPY pom.xml .
COPY src ./src

# Budowanie aplikacji Spring Boot
RUN mvn clean package -DskipTests

# Etap uruchomieniowy - użycie lekkiego obrazu Javy
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Kopiowanie zbudowanego pliku JAR
COPY --from=builder /app/target/*.jar app.jar

# Eksponowanie portu aplikacji
EXPOSE 8080

# Uruchomienie aplikacji
CMD ["java", "-jar", "app.jar"]
