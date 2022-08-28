package org.industry.center.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.feign.ProfileClient;
import org.industry.center.manager.service.NotifyService;
import org.industry.center.manager.service.ProfileService;
import org.industry.common.bean.R;
import org.industry.common.constant.CommonConstant;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.ProfileDto;
import org.industry.common.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.PROFILE_URL_PREFIX)
public class ProfileApi implements ProfileClient {

    @Resource
    private NotifyService notifyService;
    @Resource
    private ProfileService profileService;

    @Override
    public R<Profile> add(Profile profile, String tenantId) {
        try {
            Profile add = profileService.add(profile.setTenantId(tenantId));
            if (null != add) {
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
            Profile profile = profileService.selectById(id);
            if (null != profile && profileService.delete(id)) {
                notifyService.notifyDriverProfile(CommonConstant.Driver.Profile.DELETE, profile);
                return R.ok();
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Profile> update(Profile profile, String tenantId) {
        try {
            Profile update = profileService.update(profile.setTenantId(tenantId));
            if (null != update) {
                notifyService.notifyDriverProfile(CommonConstant.Driver.Profile.UPDATE, update);
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Profile> selectById(String id) {
        try {
            Profile select = profileService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Profile>> selectByDeviceId(String deviceId) {
        try {
            List<Profile> select = profileService.selectByDeviceId(deviceId);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<Profile>> list(ProfileDto profileDto, String tenantId) {
        try {
            profileDto.setTenantId(tenantId);
            Page<Profile> page = profileService.list(profileDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}
