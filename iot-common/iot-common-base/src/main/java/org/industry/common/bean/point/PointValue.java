package org.industry.common.bean.point;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.industry.common.constant.CommonConstant;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * MongoDB 位号数据
 *
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @MongoId
    private String id;

    /**
     * 设备ID，同MySQl中等 设备ID 一致
     */
    private String deviceId;

    /**
     * 位号ID，同MySQl中等 位号ID 一致
     */
    private String pointId;

    /**
     * 处理值，进行过缩放、格式化等操作
     */
    private String value;

    /**
     * 原始值
     */
    private String rawValue;

    /**
     * 计算值
     */
    private Object calculateValue;

    private List<PointValue> children;

    @Transient
    private Short rw;

    @Transient
    private String unit;

    @Transient
    private String type;

    @Transient
    private Integer timeOut = 15;

    @Transient
    private TimeUnit timeUnit = TimeUnit.MINUTES;

    private Boolean multi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date originTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    public PointValue(String pointId, String rawValue, String value) {
        this.pointId = pointId;
        this.rawValue = rawValue;
        this.value = value;
    }

    public PointValue(String deviceId, String pointId, String rawValue, String value) {
        this.deviceId = deviceId;
        this.pointId = pointId;
        this.rawValue = rawValue;
        this.value = value;
        this.originTime = new Date();
    }

    public PointValue(String deviceId, String pointId, String rawValue, String value, int timeOut, TimeUnit timeUnit) {
        this.deviceId = deviceId;
        this.pointId = pointId;
        this.rawValue = rawValue;
        this.value = value;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.originTime = new Date();
    }

    public PointValue(String deviceId, List<PointValue> children) {
        this.deviceId = deviceId;
        this.children = children;
        this.originTime = new Date();
    }

    public PointValue(String deviceId, List<PointValue> children, int timeOut, TimeUnit timeUnit) {
        this.deviceId = deviceId;
        this.children = children;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.originTime = new Date();
    }
}
