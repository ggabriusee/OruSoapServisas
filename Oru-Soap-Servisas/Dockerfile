FROM maven:3.3.9-jdk-8

WORKDIR /code
COPY ./*.xml /code/
COPY ./src /code/src
RUN ["mvn", "clean", "package"]

EXPOSE 5000
CMD ["java", "-jar", "target/Oru-Soap-Servisas-0.0.1-SNAPSHOT.jar"]
