package org.industry.driver.mqtt.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.industry.common.utils.JsonUtil;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MqttReceiveHandler {
    private DataType dataType = DataType.DEFAULT;
    private String data;

    public MqttReceiveHandler(DataType dataType, Object target) {
        this.dataType = dataType;
        this.data = JsonUtil.toJsonString(target);
    }

    @NoArgsConstructor

    public enum DataType {
        OPC_UA, OPC_DA, MODEBUS, PLC, SERIAL, SOCKET, HEARTBEAT, DEFAULT
    }
}
