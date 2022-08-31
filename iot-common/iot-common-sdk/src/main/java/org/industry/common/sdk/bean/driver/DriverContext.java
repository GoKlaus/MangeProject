package org.industry.common.sdk.bean.driver;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.driver.AttributeInfo;
import org.industry.common.bean.driver.DriverMetadata;
import org.industry.common.constant.CommonConstant;
import org.industry.common.exception.NotFoundException;
import org.industry.common.model.Device;
import org.industry.common.model.Point;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class DriverContext {

    /**
     * 驱动元数据，当且仅当驱动注册成功之后由 Manager 返回
     */
    private DriverMetadata driverMetadata = new DriverMetadata();

    /**
     * 驱动状态，默认为 未注册 状态
     */
    private String driverStatus = CommonConstant.Status.UNREGISTERED;

    public synchronized void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    /**
     * 查询
     *
     * @param deviceId 设备ID
     * @return
     */
    public Device getDeviceByDeviceId(String deviceId) {
        Device device = this.driverMetadata.getDeviceMap().get(deviceId);
        if(null == device) {
            throw new NotFoundException("Device(" + deviceId + ") does not exist");
        }
        return device;
    }

    /**
     * 根据 设备ID 获取连接设备的驱动配置信息
     *
     * @param deviceId
     * @return
     */
    public Map<String, AttributeInfo> getDriverInfoByDeviceId(String deviceId) {
        return this.driverMetadata.getDriverInfoMap().get(deviceId);
    }

    /**
     * 根据 设备ID 获取连接设备的全部位号配置信息
     *
     * @param deviceId
     * @return
     */
    public Map<String, Map<String, AttributeInfo>> getPointInfoByDeviceId(String deviceId) {
        Map<String, Map<String, AttributeInfo>> result = this.driverMetadata.getPointInfoMap().get(deviceId);
        if(null==result||result.size() < 1){
            throw new NotFoundException("Device(" + deviceId + ") does not exist");
        }
        return result;
    }

    /**
     * 根据 设备Id 和 位号Id 获取连接设备的位号配置信息
     *
     * @param deviceId Device Id
     * @param pointId  Point Id
     * @return Map<String, AttributeInfo>
     */
    public Map<String, AttributeInfo> getPointInfoByDeviceIdAndPointId(String deviceId, String pointId) {
        Map<String, AttributeInfo> result = getPointInfoByDeviceId(deviceId).get(pointId);
        if(null== result || result.size()<0){
            throw new NotFoundException("Point(" + pointId + ") info does not exist");
        }
        return result;
    }

    /**
     * 根据 设备Id 获取位号
     *
     * @param deviceId Device Id
     * @return Point Array
     */
    public List<Point> getPointByDeviceId(String deviceId) {
        Device device = getDeviceByDeviceId(deviceId);
        return this.driverMetadata.getProfilePointMap().entrySet().stream()
                .filter(entry -> device.getProfileIds().contains(entry.getKey()))
                .map(entry -> new ArrayList<>(entry.getValue().values()))
                .reduce(new ArrayList<>(), (total, temp) -> {
                    total.addAll(temp);
                    return total;
                });
    }

    /**
     * 根据 设备Id和位号Id 获取位号
     *
     * @param deviceId Device Id
     * @param pointId  Point Id
     * @return Point
     */
    public Point getPointByDeviceIdAndPointId(String deviceId, String pointId) {
        Device device = getDeviceByDeviceId(deviceId);
        Optional<Map<String, Point>> optional = this.driverMetadata.getProfilePointMap().entrySet().stream()
                .filter(entry -> device.getProfileIds().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .filter(entry -> entry.containsKey(pointId))
                .findFirst();

        if (optional.isPresent()) {
            return optional.get().get(pointId);
        }

        throw new NotFoundException("Point(" + pointId + ") point does not exist");
    }


}
