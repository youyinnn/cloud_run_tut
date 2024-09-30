FROM maven:3.9.4-eclipse-temurin-17

WORKDIR ./app
COPY . .

RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

ENTRYPOINT ["./mvnw", "spring-boot:run"]