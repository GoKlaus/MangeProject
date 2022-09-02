package org.industry.driver.mqtt.handler;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MqttSendHandler {
    /**
     * 使用 Default Topic & Default Qos 发送数据
     *
     * @param data
     */
    void sendToMqtt(String data);

    /**
     * 使用 Default Topic & 自定义 Qos 发送数据
     *
     * @param qos
     * @param data
     */
    void sendToMqtt(@Header(MqttHeaders.QOS) Integer qos, String data);

    /**
     * 使用 自定义 Topic & Default Qos 发送数据
     *
     * @param topic
     * @param data
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);

    /**
     * 使用 自定义 Topic & 自定义 Qos 发送数据
     *
     * @param topic
     * @param qos
     * @param data
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) Integer qos, String data);
}
