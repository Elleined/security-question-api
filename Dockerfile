FROM openjdk:17-alpine
MAINTAINER Elleined

ENV MYSQL_HOST=mysql_server
ENV MYSQL_PORT=3306
ENV MYSQL_DB=sqa_db
ENV MYSQL_USER=root
ENV MYSQL_PASS=root
ENV PORT=8087

ADD ./target/*.jar security-question-api.jar
EXPOSE 8087
CMD ["java", "-jar", "security-question-api.jar"]