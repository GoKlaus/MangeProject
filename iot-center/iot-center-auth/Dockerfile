FROM klaus/corretto:11-alpine
MAINTAINER klaus <klaus1515z@gmail.com>

ENV JAVA_OPS -server \
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
             -XX:+UseG1GC \
             -XX:+ExplicitGCInvokesConcurrent \
             -Xlog:gc*,safepoint:iot/logs/center/auth/gc/iot-center-auth-gc.log:time,uptime:filecount=20,filesize=20M

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

WORKDIR /iot-center/iot-center-auth
ADD ./target/iot-center-auth.jar ./


EXPOSE 8300
VOLUME /iot-center/iot-center-auth/iot/logs

CMD mkdir -p /iot-center/iot-center-auth/iot/logs/center/auth/gc \
    && java ${JAVA_OPS} ${GC_LOG} -jar iot-center-auth.jar