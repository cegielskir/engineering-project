#FROM openjdk:8-jdk-alpine
#
#VOLUME /tmp
#
#COPY discovery-service/mvnw wip/
#COPY discovery-service/.mvn wip/.mvn
#COPY discovery-service/pom.xml wip/
#COPY discovery-service/src wip/src
#COPY discovery-service/Dockerfile wip/Dockerfile
#COPY pom.xml .
#
#WORKDIR "/wip"
#
#RUN ./mvnw install dockerfile:build
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.discoveryservice.DiscoveryServiceApplication"]
#EXPOSE 8061

FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY discovery-service/mvnw wip/
COPY discovery-service/.mvn wip/.mvn
COPY discovery-service/pom.xml wip/
COPY discovery-service/src wip/src
COPY discovery-service/Dockerfile wip/Dockerfile
COPY pom.xml .

WORKDIR "/wip"

RUN ./mvnw dependency:go-offline -B

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=/wip/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.discoveryservice.DiscoveryServiceApplication"]