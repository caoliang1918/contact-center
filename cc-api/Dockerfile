FROM openjdk:17-jdk-oracle
MAINTAINER caoliang1918 <caoliang1918@gmail.com>

WORKDIR /webapps
ADD target/cc-api-${project.version}.jar /webapps/lib/
ADD target/classes/logback-spring.xml /webapps/conf/logback-spring.xml
ADD target/classes/bootstrap.properties /webapps/conf/bootstrap.properties
ADD target/classes/application-prod.properties /webapps/conf/application-prod.properties
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xmn1g -Xmx2g -Xms2g"
ENV RUN_ARGS="--spring.config.location=file:/webapps/conf/ --spring.profiles.active=nacos --logging.file.path=/webapps/logs"
ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=UTF-8 -XX:+UseG1GC $JAVA_OPTS -jar /webapps/lib/cc-api-*.jar $RUN_ARGS" ]
