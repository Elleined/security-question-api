FROM openjdk:17-alpine
MAINTAINER Elleined

ENV MYSQL_HOST=mysql_server
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=sqa_db
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV PORT=8087
ENV EXPIRATION=86400000
ENV REFRESH_TOKEN_EXPIRATION=86400000

ADD ./target/*.jar security-question-api.jar
EXPOSE 8087
CMD ["java", "-jar", "security-question-api.jar"]