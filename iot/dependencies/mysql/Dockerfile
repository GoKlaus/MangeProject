FROM mysql:5.7
MAINTAINER klaus <klaus1515z@gmail.com>

ENV MYSQL_ROOT_PASSWORD admin@123

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./iot-dc3.sql /docker-entrypoint-initdb.d