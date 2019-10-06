FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY main-service/mvnw wip/
COPY main-service/.mvn wip/.mvn
COPY main-service/pom.xml wip/
COPY main-service/src wip/src
COPY main-service/Dockerfile wip/Dockerfile
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
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.mainservice.MainServiceApplication"]