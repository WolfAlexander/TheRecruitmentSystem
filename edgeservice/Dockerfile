FROM java:8
VOLUME /tmp
ADD target/edge-service-0.2.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]