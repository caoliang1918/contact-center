FROM openjdk:8-jdk-alpine
VOLUME /webapps
ADD fs-api/target/fs-api-*.jar /webapps/lib/fs-api.jar
ENV JAVA_OPTS=" -Xms1024m -Xmx1024m"
EXPOSE 7200 7260

WORKDIR /webapps/lib
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar --spring.config.location=file:/webapps/conf/,classpath:/boosstrap.properties --spring.profiles.active=nacos