FROM eclipse-temurin:17-jre
VOLUME /tmp
COPY build/libs/*.jar app.jar
COPY build/reports static/reports
ENV PROFILE=default
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]
