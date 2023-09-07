FROM openjdk:17.0.2-jdk-slim-buster
COPY target/*.jar MyHouseUser24.jar
RUN apt-get update && apt-get install -y default-mysql-client
ENTRYPOINT ["java", "-jar", "MyHouseUser24.jar"]