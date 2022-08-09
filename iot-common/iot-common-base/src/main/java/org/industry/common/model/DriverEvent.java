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
public class DriverEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * MongoDB Object Id
     */
    @MongoId
    private ObjectId id;

    private String serviceName;

    /**
     * Driver Event
     * <p>
     * STATUS、ERROR
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

    public DriverEvent(String serviceName, String type, Object content) {
        this.serviceName = serviceName;
        this.type = type;
        this.content = content;
        this.originTime = System.currentTimeMillis();
    }

    public DriverEvent(String serviceName, String type, Object content, int timeOut, TimeUnit timeUnit) {
        this.serviceName = serviceName;
        this.type = type;
        this.content = content;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.originTime = System.currentTimeMillis();
    }
}
