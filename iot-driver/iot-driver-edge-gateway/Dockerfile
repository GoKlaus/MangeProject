FROM klaus/corretto:11-alpine
MAINTAINER klaus <klaus1515z@gmail.com>

ENV JAVA_OPS -server \
             # aspectj 运行时织入
             -javaagent:/usr/local/java/lib/aspectjweaver.jar \
             -Xms128m \
             -Xmx2048m \
             -Xss256k \
             -Djava.security.egd=file:/dev/./urandom \
             -XX:MaxDirectMemorySize=256m \
             -XX:+UseCompressedOops \
             -XX:+UseCompressedClassPointers \
             -XX:+SegmentedCodeCache \
             -XX:+PrintCommandLineFlags
ENV GC_LOG   -verbose:gc \
             # 使用G1垃圾回收器
             -XX:+UseG1GC \
             -XX:+ExplicitGCInvokesConcurrent \
             -Xlog:gc*:iot/logs/driver/edge-gateway/gc/iot-driver-edge-gateway-gc.log

RUN ln -sf /usr/share/zoneinfo/Asiz/Shanghai /etc/localtime

WORKDIR /iot-driver/iot-driver-edge-gateway

ADD ./target/iot-driver-edge-gateway.jar ./
EXPOSE 8701

VOLUME /iot-driver/iot-driver-edge-gateway/iot/logs

CMD mkdir -p /iot-driver/iot-driver-edge-gateway/iot/logs/driver/edge-gateway/gc \
 && java ${JAVA_OPS} ${GC_LOG} -jar iot-driver-edge-geteway.jar