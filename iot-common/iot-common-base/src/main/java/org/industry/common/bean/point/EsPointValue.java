package org.industry.common.bean.point;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.industry.common.constant.CommonConstant;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * MongoDB 位号数据
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EsPointValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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

    private List<EsPointValue> children;

    private Boolean multi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date originTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    public EsPointValue(String pointId, String rawValue, String value) {
        this.pointId = pointId;
        this.rawValue = rawValue;
        this.value = value;
    }

    public EsPointValue(String deviceId, String pointId, String rawValue, String value) {
        this.deviceId = deviceId;
        this.pointId = pointId;
        this.rawValue = rawValue;
        this.value = value;
        this.originTime = new Date();
    }

    public EsPointValue(String deviceId, List<EsPointValue> children) {
        this.deviceId = deviceId;
        this.children = children;
        this.originTime = new Date();
    }
}
