package org.industry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * MongoDB Object Id
     */
    @MongoId
    private ObjectId id;

    /**
     * 设备ID，同MySQl中等 设备ID 一致
     */
    private String deviceId;

    /**
     * 位号ID，同MySQl中等 位号ID 一致
     */
    private String pointId;

    /**
     * Device Event
     * <p>
     * STATUS、LIMIT、ERROR
     */
    private String type;

    private Boolean confirm = false;
    private Object content;

    @Transient
    private int timeOut = 15;

    @Transient
    private TimeUnit timeUnit = TimeUnit.MINUTES;

    private Long originTime;
    private Long confirmTime;

    public DeviceEvent(String deviceId, String type, Object content) {
        this.deviceId = deviceId;
        this.type = type;
        this.content = content;
        this.originTime = System.currentTimeMillis();
    }

    public DeviceEvent(String deviceId, String type, Object content, int timeOut, TimeUnit timeUnit) {
        this.deviceId = deviceId;
        this.type = type;
        this.content = content;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.originTime = System.currentTimeMillis();
    }

    public DeviceEvent(String deviceId, String pointId, String type, Object content) {
        this.deviceId = deviceId;
        this.pointId = pointId;
        this.type = type;
        this.content = content;
        this.originTime = System.currentTimeMillis();
    }

    public DeviceEvent(String deviceId, String pointId, String type, Object content, int timeOut, TimeUnit timeUnit) {
        this.deviceId = deviceId;
        this.pointId = pointId;
        this.type = type;
        this.content = content;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.originTime = System.currentTimeMillis();
    }
}
