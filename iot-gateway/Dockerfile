FROM klaus/corretto:11-alpine
MAINTAINER klaus <klaus1515z@gmail.com>

ENV JAVA_OPS -server \
             -javaagent:/usr/local/java/lib/aspectjweaver.jar \
             -Xms128m \
             -Xmx2048m \
             -Xss512k \
             -Djava.security.egd=file:/dev/./urandom \
             # 堆外内存大小
             -XX:MaxDirectMemorySize=256m \
             # 普通对象压缩指针
             -XX:+UseCompressedOops \
             # 类指针压缩
             -XX:+UseCompressedClassPointers \
             # jdk9的特性
             -XX:+SegmentedCodeCache \
             -XX:+PrintCommandLineFlags
ENV GC_LOG   -verbose:gc \
             -XX:+UseG1GC \
             -XX:+ExplicitGCInvokesConcurrent \
             -Xlog:gc*,safepoint:iot/logs/gateway/gc/iot-gateway-gc.log

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

WORKDIR /iot-gateway

ADD ./target/iot-gateway.jar ./

EXPOSE 8000
VOLUME /iot-gateway/iot/logs

CMD mkdir -p /iot-gateway/iot/logs/gateway/gc \
    && java ${JAVA_OPS} ${GC_LOG} -jar iot-gateway.jar