FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} phonebook-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/phonebook-0.0.1.jar"]