FROM maven:3.9.11-amazoncorretto-21
WORKDIR /app
COPY . .
RUN mvn package -DskipTests
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "target/medilabopatient-0.0.1-SNAPSHOT.jar"]
