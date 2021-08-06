FROM openjdk:8-jdk-alpine
VOLUME /webapps
ADD cc-ivr-*.jar /webapps/lib/
ENV JAVA_OPTS=" -Xms1024m -Xmx1024m"
EXPOSE 7300

WORKDIR /webapps/lib
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar --spring.config.location=file:/app/conf/,classpath:/application.properties --spring.profiles.active=prod

