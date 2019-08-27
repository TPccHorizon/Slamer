FROM openjdk:8-alpine
WORKDIR /backend
COPY . /backend
USER root
RUN chmod +x ./gradlew build

FROM openjdk:8-jre-alpine
WORKDIR /backend
CMD ["java", "-Djava.security.egd=file:/dev/urandom", "-Djava.awt.headless=true", "-jar", "backend.jar"]
RUN ls
COPY build/libs/backend-0.0.1-SNAPSHOT.jar /backend/backend.jar