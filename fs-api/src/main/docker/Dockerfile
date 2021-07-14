FROM openjdk:8-jdk-alpine
VOLUME /webapps
ADD fs-api-*.jar /webapps/lib/fs-api.jar
ENV JAVA_OPTS=" -Xms1024m -Xmx1024m"
EXPOSE 6800 2525 6868

WORKDIR /webapps/lib
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar --spring.config.location=file:/app/conf/,classpath:/application.properties --spring.profiles.active=prod

