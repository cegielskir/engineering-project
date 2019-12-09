#syntax=docker/dockerfile:experimental
FROM maven:3.6.0-jdk-8-slim as build

VOLUME /tmp

COPY comparison-service/pom.xml wip/
COPY comparison-service/src wip/src
COPY comparison-service/Dockerfile wip/Dockerfile
COPY pom.xml .

WORKDIR "/wip"

# RUN --mount=type=cache,target=/root/.m2 mvn clean install
#RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests
RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

RUN apk --no-cache add curl 


ARG DEPENDENCY=/wip/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.comparisonservice.ComparisonServiceApplication"]