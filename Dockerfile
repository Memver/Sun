FROM eclipse-temurin:17-jre-alpine
#openjdk:17
COPY . /java
WORKDIR /java
EXPOSE 8080
CMD ["java", "-jar", "target/aggregator-0.0.1-SNAPSHOT.jar"]