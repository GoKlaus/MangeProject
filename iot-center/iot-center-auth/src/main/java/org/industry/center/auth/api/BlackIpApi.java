package org.industry.center.auth.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.service.BlackIpService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.BlackIpClient;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.model.BlackIp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.BLACK_IP_URL_PREFIX)
public class BlackIpApi implements BlackIpClient {

    @Resource
    private BlackIpService blackIpService;

    /**
     * @param blackIp
     * @return
     */
    @Override
    public R<BlackIp> add(BlackIp blackIp) {
        try {
            BlackIp add = blackIpService.add(blackIp);
            if (add != null) {
                return R.ok(add);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public R<Boolean> delete(String id) {
        try {
            return blackIpService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    /**
     * @param blackIp
     * @return
     */
    @Override
    public R<BlackIp> update(BlackIp blackIp) {
        try {
            BlackIp update = blackIpService.update(blackIp);
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public R<BlackIp> selectById(String id) {
        try {
            BlackIp select = blackIpService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param ip
     * @return
     */
    @Override
    public R<BlackIp> selectByIp(String ip) {
        try {
            BlackIp select = blackIpService.selectById(ip);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param query
     * @return
     */
    @Override
    public R<Page<BlackIp>> list(BlackIpDto query) {
        try {
            Page<BlackIp> page = blackIpService.list(query);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> checkBlackIpValid(String ip) {
        try {
            return blackIpService.checkBlackIpValid(ip) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
