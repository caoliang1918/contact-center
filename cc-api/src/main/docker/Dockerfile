FROM openjdk:8-jdk-alpine
VOLUME /app
ADD cc-openapi-*.jar /app/lib/cc-openapi.jar
RUN sh -c 'touch /app/lib/app.jar'
ENV JAVA_OPTS=" -Xms1024m -Xmx1024m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]

