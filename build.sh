#!/bin/sh
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }

git pull

mvn clean install -DskipTests=true

#提取版本
TARGET=`ls cc-api/target/ | grep gz|grep -v original`
MAVEN_VERSION=${TARGET%-*}
version=${MAVEN_VERSION:7}

echoGreen "maven build project version:${version} success!"

docker build -t cc-api:${version} --target cc-api .
echoGreen "build docker image cc-api:${version} success!"

docker build -t fs-api:${version} --target fs-api .
echoGreen "build docker image fs-api:${version} success!"

docker build -t cc-ivr:${version} --target cc-ivr .
echoGreen "build docker image cc-ivr:${version} success!"
