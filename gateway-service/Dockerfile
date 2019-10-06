FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY gateway-service/mvnw wip/
COPY gateway-service/.mvn wip/.mvn
COPY gateway-service/pom.xml wip/
COPY gateway-service/src wip/src
COPY gateway-service/Dockerfile wip/Dockerfile
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
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cgk.engineering.team.gatewayservice.GatewayServiceApplication"]