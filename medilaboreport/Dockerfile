FROM maven:3.9.11-amazoncorretto-21
WORKDIR /app
COPY . .
RUN mvn package -DskipTests
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "target/medilaboreport-0.0.1-SNAPSHOT.jar"]
