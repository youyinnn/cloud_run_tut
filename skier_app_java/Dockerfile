FROM maven:3.9.4-eclipse-temurin-11


COPY . ./project
WORKDIR ./project

EXPOSE 8080
ENTRYPOINT ["mvn", "clean", "install", "exec:exec", "-Dmaven.test.skip=true"]