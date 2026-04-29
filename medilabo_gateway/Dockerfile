FROM maven:3.9.11-amazoncorretto-21
WORKDIR /app
COPY . .
RUN mvn package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/gateway-0.0.1-SNAPSHOT.jar"]
