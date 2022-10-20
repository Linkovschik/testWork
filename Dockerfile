FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootWar

FROM openjdk:17-alpine
COPY --from=build /home/gradle/src/build/libs/testWork-0.0.1-SNAPSHOT.war /usr/local/lib/testWork.war
ENTRYPOINT ["java", "-jar", "/usr/local/lib/testWork.war"]