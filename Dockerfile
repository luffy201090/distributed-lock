FROM adoptopenjdk/openjdk15:jdk-15.0.1_9-alpine
RUN mkdir /opt/app
COPY target/distributed-lock-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/distributed-lock-0.0.1-SNAPSHOT.jar"]