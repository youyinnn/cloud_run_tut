FROM maven:3.9.4-eclipse-temurin-11


COPY . ./project
WORKDIR ./project

ENTRYPOINT ["mvn", "clean", "install", "exec:exec", "-Dmaven.test.skip=true"]