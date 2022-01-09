FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/menusrv-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} menusrv.jar
ENTRYPOINT ["java","-jar","/menusrv.jar"]