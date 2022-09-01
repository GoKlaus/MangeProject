package org.industry.common.sdk.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.point.PointValue;
import org.industry.common.constant.CommonConstant;
import org.industry.common.constant.ValueConstant;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.DeviceEvent;
import org.industry.common.model.DriverEvent;
import org.industry.common.model.Point;
import org.industry.common.sdk.bean.driver.DriverContext;
import org.industry.common.sdk.service.DriverService;
import org.industry.common.utils.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    @Value("${spring.application.name}")
    private String serviceName;
    @Resource
    private DriverContext driverContext;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ApplicationContext applicationContext;

    /**
     * 将位号原始值进行处理和转换
     *
     * @param deviceId
     * @param pointId
     * @param rawValue
     * @return
     */
    @Override
    public String convertValue(String deviceId, String pointId, String rawValue) {
        String value;
        Point point = driverContext.getPointByDeviceIdAndPointId(deviceId, pointId);

        switch (point.getType()) {
            case ValueConstant.Type.STRING:
                value = rawValue;
                break;
            case ValueConstant.Type.BYTE:
            case ValueConstant.Type.SHORT:
            case ValueConstant.Type.INT:
            case ValueConstant.Type.LONG:
            case ValueConstant.Type.DOUBLE:
            case ValueConstant.Type.FLOAT:
                try {
                    float base = null != point.getBase() ? point.getBase() : 0;
                    float multiple = null != point.getMultiple() ? point.getMultiple() : 1;
                    double temp = (Convert.convert(Double.class, rawValue.trim()) + base) * multiple;
                    if (null != point.getMinimum() && temp < point.getMinimum()) {
                        log.info("Device({}) point({}) value({}) is lower than lower limit({})", deviceId, pointId, temp, point.getMinimum());
                        deviceEventSender(deviceId, pointId, CommonConstant.Device.Event.OVER_LOWER_LIMIT,
                                String.format("Value(%s) is lower then lower limit %s", temp, point.getMinimum()));
                    }
                    if (null != point.getMaximum() && temp > point.getMaximum()) {
                        log.info("Device({}) point({}) value({}) is greater than upper limit({})", deviceId, pointId, temp, point.getMaximum());
                        deviceEventSender(deviceId, pointId, CommonConstant.Device.Event.OVER_UPPER_LIMIT,
                                String.format("Value(%s) is greater than upper limit %s", temp, point.getMaximum()));
                    }
                    if (StrUtil.isNotBlank(point.getFormat())) {
                        value = String.format(point.getFormat(), temp);
                    } else {
                        value = String.valueOf(temp);
                    }

                } catch (Exception e) {
                    throw new ServiceException("Invalid device({}) point({}) value({}), error: {}", deviceId, pointId, rawValue, e.getMessage());
                }
                break;
            case ValueConstant.Type.BOOLEAN:
                try {
                    try {
                        Double booleanValue = Convert.convert(Double.class, rawValue.trim());
                        if (booleanValue > 0) {
                            value = Boolean.TRUE.toString();
                        } else {
                            value = Boolean.FALSE.toString();
                        }
                    } catch (Exception e) {
                        value = String.valueOf(Boolean.parseBoolean(rawValue.trim()));
                    }
                } catch (Exception e) {
                    throw new ServiceException("Invalid device({}) point({}) value({}), error: {}", deviceId, pointId, rawValue, e.getMessage());
                }
                break;
            default:
                throw new ServiceException("Invalid device({}) point({}) value({}) type: {} ", deviceId, pointId, rawValue, point.getType());
        }
        return value;
    }

    /**
     * 发送驱动事件
     *
     * @param driverEvent
     */
    @Override
    public void driverEventSender(DriverEvent driverEvent) {
        if (null != driverEvent) {
            log.debug("Send Driver Event: {}", JsonUtil.toJsonString(driverEvent));
            rabbitTemplate.convertAndSend(CommonConstant.Rabbit.TOPIC_EXCHANGE_EVENT, CommonConstant.Rabbit.ROUTING_DRIVER_EVENT_PREFIX + serviceName, driverEvent);
        }
    }

    /**
     * 发送设备事件
     *
     * @param deviceEvent
     */
    @Override
    public void deviceEventSender(DeviceEvent deviceEvent) {
        if (null != deviceEvent) {
            log.debug("Send Device Event: {}", JsonUtil.toJsonString(deviceEvent));
            rabbitTemplate.convertAndSend(CommonConstant.Rabbit.TOPIC_EXCHANGE_EVENT, CommonConstant.Rabbit.ROUTING_DRIVER_EVENT_PREFIX + serviceName, deviceEvent);
        }
    }

    /**
     * 发送设备事件
     *
     * @param deviceId
     * @param type
     * @param content
     */
    @Override
    public void deviceEventSender(String deviceId, String type, String content) {
        deviceEventSender(new DeviceEvent(deviceId, type, content));
    }

    /**
     * 发送设备事件
     *
     * @param deviceId
     * @param pointId
     * @param type
     * @param content
     */
    @Override
    public void deviceEventSender(String deviceId, String pointId, String type, String content) {
        deviceEventSender(new DeviceEvent(deviceId, pointId, type, content));
    }

    /**
     * 发送位号值到消息组件
     *
     * @param pointValue
     */
    @Override
    public void pointValueSender(PointValue pointValue) {
        if (null != pointValue) {
            log.debug("Send point value: {}", JsonUtil.toJsonString(pointValue));
            rabbitTemplate.convertAndSend(
                    CommonConstant.Rabbit.TOPIC_EXCHANGE_VALUE,
                    CommonConstant.Rabbit.ROUTING_POINT_VALUE_PREFIX + serviceName,
                    pointValue
            );
        }
    }

    /**
     * 批量发送位号值到消息组件
     *
     * @param pointValues
     */
    @Override
    public void pointValueSender(List<PointValue> pointValues) {
// TODO 需要添加新的队列支持list数据发送
        if (null != pointValues) {
            pointValues.forEach(this::pointValueSender);
        }
    }

    /**
     * Close ApplicationContext
     *
     * @param template
     * @param params
     */
    @Override
    public void close(CharSequence template, Object... params) {
        log.error(StrUtil.format(template, params));
        ((ConfigurableApplicationContext) applicationContext).close();
        System.exit(1);
    }
}
