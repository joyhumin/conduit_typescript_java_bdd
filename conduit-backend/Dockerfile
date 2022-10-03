# syntax=docker/dockerfile:1
# todo? which way is better, create build while containerise or just simply copy  the jar file
# Don't need JDK if only we copy the jar file to
FROM openjdk:17-jdk-alpine as builder
# define variables
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} conduit-backend.jar
# EXTRACT the spring boot layers
RUN java -Djarmode=layertools -jar conduit-backend.jar extract

FROM openjdk:17-jdk-alpine as runtime
# Second stage : Copy the extracted layers
# ref: https://www.baeldung.com/docker-layers-spring-boot
WORKDIR app
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
# run docker build will failed at first time
# run it again (with out --no-cahed) will build successfully
COPY --from=builder application/ ./

# note: The ENTRYPOINT specifies a command that will always be executed when the container starts.
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
