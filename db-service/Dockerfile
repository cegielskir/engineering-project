FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY db-service/mvnw wip/
COPY db-service/.mvn wip/.mvn
COPY db-service/pom.xml wip/
COPY db-service/src wip/src
COPY db-service/Dockerfile wip/Dockerfile
COPY pom.xml .

WORKDIR "/wip"

RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

RUN apk --no-cache add curl 

ARG DEPENDENCY=/wip/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.dbservice.DbServiceApplication"]