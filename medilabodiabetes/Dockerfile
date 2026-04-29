FROM maven:3.9.11-amazoncorretto-21
COPY src/main/resources/lib/com/ocab /root/.m2/repository/com/ocab
WORKDIR /app
COPY . .

RUN mvn package -DskipTests
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "target/mediladiabetes-0.0.1-SNAPSHOT.jar"]
