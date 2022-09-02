package org.industry.common.sdk.bean.mqtt;

import lombok.Data;
import lombok.experimental.Accessors;
import org.industry.common.utils.JsonUtil;

@Data
@Accessors(chain = true)
public class MessagePayload {
    private String payload;
    private MessageType messageType;

    public MessagePayload() {
        this.messageType = MessageType.DEFAULT;
    }

    public MessagePayload(Object payload, MessageType messageType) {
        this.payload = JsonUtil.toJsonString(payload);
        this.messageType = messageType;
    }
}
