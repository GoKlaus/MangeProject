package org.industry.center.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.feign.PointClient;
import org.industry.center.manager.service.NotifyService;
import org.industry.center.manager.service.PointService;
import org.industry.common.bean.R;
import org.industry.common.constant.CommonConstant;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.PointDto;
import org.industry.common.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 位号 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.POINT_URL_PREFIX)
public class PointApi implements PointClient {

    @Resource
    private PointService pointService;
    @Resource
    private NotifyService notifyService;

    @Override
    public R<Point> add(Point point, String tenantId) {
        try {
            Point add = pointService.add(point.setTenantId(tenantId));
            if (null != add) {
                notifyService.notifyDriverPoint(CommonConstant.Driver.Point.ADD, add);
                return R.ok(add);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> delete(String id) {
        try {
            Point point = pointService.selectById(id);
            if (null != point && pointService.delete(id)) {
                notifyService.notifyDriverPoint(CommonConstant.Driver.Point.DELETE, point);
                return R.ok();
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Point> update(Point point, String tenantId) {
        try {
            Point update = pointService.update(point.setTenantId(tenantId));
            if (null != update) {
                notifyService.notifyDriverPoint(CommonConstant.Driver.Point.UPDATE, update);
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Point> selectById(String id) {
        try {
            Point select = pointService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Point>> selectByProfileId(String profileId) {
        try {
            List<Point> select = pointService.selectByProfileId(profileId);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Point>> selectByDeviceId(String deviceId) {
        try {
            List<Point> select = pointService.selectByDeviceId(deviceId);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<Point>> list(PointDto pointDto, String tenantId) {
        try {
            pointDto.setTenantId(tenantId);
            Page<Point> page = pointService.list(pointDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Map<String, String>> unit(Set<String> pointIds) {
        try {
            Map<String, String> units = pointService.unit(pointIds);
            if (null != units) {
                return R.ok(units);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}
