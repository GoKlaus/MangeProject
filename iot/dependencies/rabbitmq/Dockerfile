FROM rabbitmq:3.8-alpine
MAINTAINER klaus <klaus1515z@gmail.com>

COPY ./rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && rm -f /etc/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf \
    && rabbitmq-plugins enable --offline rabbitmq_management rabbitmq_mqtt rabbitmq_stomp