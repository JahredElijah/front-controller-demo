FROM tomcat:8.0-jre8

LABEL maintainer="Jahred Boyd"

ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["catalina.sh", "run]