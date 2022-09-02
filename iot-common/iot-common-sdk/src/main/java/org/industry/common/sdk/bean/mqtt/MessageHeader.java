package org.industry.common.sdk.bean.mqtt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.MessageHeaders;

import java.util.UUID;

@Setter
@Getter
public class MessageHeader {
    private String id;
    private Integer mqttId;
    private Integer mqttReceivedQos;
    private String mqttReceivedTopic;
    private Boolean mqttDuplicate;
    private Boolean mqttReceivedRetained;
    private Long timestamp;

    public MessageHeader(MessageHeaders messageHeaders) {
        if (null != messageHeaders) {
            try {
                UUID id = messageHeaders.get("id", UUID.class);
                if (null != id) {
                    this.id = id.toString();
                }
            } catch (Exception ignored) {
            }
            try {
                this.mqttId = messageHeaders.get("mqtt_id", Integer.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedQos = messageHeaders.get("mqtt_receivedQos", Integer.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedTopic = messageHeaders.get("mqtt_receivedTopic", String.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttDuplicate = messageHeaders.get("mqtt_duplicate", Boolean.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedRetained = messageHeaders.get("mqtt_receivedRetained", Boolean.class);
            } catch (Exception ignored) {
            }
            try {
                this.timestamp = messageHeaders.get("timestamp", Long.class);
            } catch (Exception ignored) {
            }
        }

    }
}
