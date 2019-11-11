FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY config-service/mvnw wip/
COPY config-service/.mvn wip/.mvn
COPY config-service/pom.xml wip/
COPY config-service/src wip/src
COPY config-service/Dockerfile wip/Dockerfile
COPY pom.xml .

WORKDIR "/wip"

RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

RUN apk --no-cache add curl 

EXPOSE 8888

ARG DEPENDENCY=/wip/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.configservice.ConfigServiceApplication"]