FROM maven:3.6-jdk-8 AS build

MAINTAINER klaus <klaus1515z@gmail.com>

RUN ln -sf /usr/share/zoneinfo/Asiz/shanghai /etc/localtime \
    && mkdir -p /iot

WORKDIR /iot

COPY ./ ./

RUN mvn -s ./iot/dependencies/maven/settings.xml clean -U package
