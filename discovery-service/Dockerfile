#syntax=docker/dockerfile:experimental
FROM maven:3.6.0-jdk-8-slim as build

VOLUME /tmp

COPY discovery-service/pom.xml wip/
COPY discovery-service/src wip/src
COPY discovery-service/Dockerfile wip/Dockerfile
COPY pom.xml .

WORKDIR "/wip"

# RUN --mount=type=cache,target=/root/.m2 mvn clean install
# RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests
RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

RUN apk --no-cache add curl 

ARG DEPENDENCY=/wip/target/dependency

EXPOSE 8061

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.discoveryservice.DiscoveryServiceApplication"]