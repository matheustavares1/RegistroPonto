FROM openjdk:21-jdk

COPY target/registroPonto-0.0.1-SNAPSHOT.jar /app/punch-clock.jar

CMD ["java", "-jar", "/app/punch-clock.jar"]