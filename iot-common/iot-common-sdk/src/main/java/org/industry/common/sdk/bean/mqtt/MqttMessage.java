package org.industry.common.sdk.bean.mqtt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MqttMessage {
    private MessageHeader messageHeader;
    private MessagePayload messagePayload;
}
