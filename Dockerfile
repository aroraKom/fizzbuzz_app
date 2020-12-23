
FROM adoptopenjdk/openjdk11:alpine-slim

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src
RUN ./mvnw package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/fizzbuzz-0.0.1-SNAPSHOT.jar"]