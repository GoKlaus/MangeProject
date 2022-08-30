FROM redis:6.2.6-alpine
MAINTAINER klaus <klaus1515z@gmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./redis.conf /usr/local/etc/redis/redis.conf

CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]