FROM mongo:5.0.5

MAINTAINER klaus <klaus1515z@gmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && mkdir -p /dc3-mongo/config/

WORKDIR /dc3-mongo

COPY ./iot-dc3.js  ./config/
COPY ./launch.sh   ./

RUN apt update \
    && apt install tofrodos \
    && apt clean \
    && fromdos ./launch.sh \
    && chmod +x ./launch.sh

EXPOSE 27017

CMD [ "/dc3-mongo/launch.sh"]
