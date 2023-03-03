FROM openjdk:openjdk:8-jdk-alpine AS cc-api
VOLUME /webapps
ADD cc-api/target/cc-api-*.jar /webapps/lib/cc-api.jar
ADD cc-api/target/classes/logback-spring.xml /webapps/conf/logback-spring.xml
ADD cc-api/target/classes/bootstrap.properties /webapps/conf/bootstrap.properties
WORKDIR /webapps
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xmn256m -Xmx1g -Xms1g"
ENV RUN_ARGS="--spring.config.location=file:/webapps/conf/ --spring.profiles.active=nacos --logging.file.path=/webapps/logs"
ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=UTF-8 -XX:+UseG1GC $JAVA_OPTS -jar /webapps/lib/cc-api.jar $RUN_ARGS" ]


FROM openjdk:8-jdk-alpine AS fs-api
VOLUME /webapps
ADD fs-api/target/fs-api-*.jar /webapps/lib/fs-api.jar
ADD fs-api/target/classes/logback-spring.xml /webapps/conf/logback-spring.xml
ADD fs-api/target/classes/bootstrap.properties /webapps/conf/bootstrap.properties
WORKDIR /webapps
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xmn256m -Xmx1g -Xms1g"
ENV RUN_ARGS="--spring.config.location=file:/webapps/conf/ --spring.profiles.active=nacos --logging.file.path=/webapps/logs"
ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=UTF-8 -XX:+UseG1GC $JAVA_OPTS -jar /webapps/lib/fs-api.jar $RUN_ARGS" ]



FROM openjdk:8-jdk-alpine AS cc-ivr
VOLUME /webapps
ADD cc-ivr/target/cc-ivr-*.jar /webapps/lib/cc-ivr.jar
ADD cc-ivr/target/classes/logback-spring.xml /webapps/conf/logback-spring.xml
ADD cc-ivr/target/classes/bootstrap.properties /webapps/conf/bootstrap.properties
WORKDIR /webapps
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xmn256m -Xmx1g -Xms1g"
ENV RUN_ARGS="--spring.config.location=file:/webapps/conf/ --spring.profiles.active=nacos --logging.file.path=/webapps/logs"
ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=UTF-8 -XX:+UseG1GC $JAVA_OPTS -jar /webapps/lib/cc-ivr.jar $RUN_ARGS" ]



 
