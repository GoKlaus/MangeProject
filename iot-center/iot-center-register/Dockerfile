FROM registry.cn-hangzhou.aliyuncs.com/klaus_yc_namespace/yc:corretto-11
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
             -Xlog:gc*:iot/logs/center/register/gc/gc-center-register.log:time,uptime:filecount=20,filesize=20M

RUN ln -sf /usr/share/zoneinfo/Asiz/Shanghai /etc/localtime
WORKDIR /iot-center/iot-center-register
ADD ./target/iot-center-register.jar ./

EXPOSE 8100
# 默认挂载匿名卷
VOLUME /iot-center/iot-center-register/iot/logs

CMD mkdir -p /iot-center/iot-center-register/iot/logs/center/register/gc \
&& java ${JAVA_OPS} ${GC_LOG} -jar iot-center-register.jar