package org.industry.common.bean.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * OpenTSDB 位号数据
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class TsPointValue implements Serializable {
    private String metric;
    private Long timestamp;
    private Object value;
    private Map<String, String> tags = new HashMap<>(4);

    public TsPointValue addTag(String tagName, String tagValue) {
        this.tags.put(tagName, tagValue);
        return this;
    }

    public TsPointValue(String metric, Integer value) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
    }

    public TsPointValue(String metric, Float value) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
    }

    public TsPointValue(String metric, String value) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
    }

    public TsPointValue(String metric, Integer value, String tagName, String tagValue) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
        addTag(tagName, tagValue);
    }

    public TsPointValue(String metric, Float value, String tagName, String tagValue) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
        addTag(tagName, tagValue);
    }

    public TsPointValue(String metric, String value, String tagName, String tagValue) {
        this.metric = metric;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
        addTag(tagName, tagValue);
    }

}
