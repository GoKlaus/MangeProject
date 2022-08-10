package org.industry.center.auth.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.service.UserService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.UserClient;
import org.industry.common.dto.UserDto;
import org.industry.common.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.USER_URL_PREFIX)
public class UserApi implements UserClient {


    @Resource
    private UserService userService;

    @Override
    public R<User> add(User user) {
        try {
            User add = userService.add(user);
            if (null != add) {
                return R.ok(user);
            }
        } catch (Exception e) {
            R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> delete(String id) {
        try {
            return userService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<User> update(User user) {
        try {
            User update = userService.update(user);
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> restPassword(String id) {
        try {
            return userService.resetPassword(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<User> selectById(String id) {
        try {
            User user = userService.selectById(id);
            if (null != user) {
                R.ok(user);
            }
        } catch (Exception e) {
            R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<User> selectByName(String name) {
        try {
            User user = userService.selectByName(name, false);
            if (null != user) {
                return R.ok(user);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<User>> list(UserDto userDto) {
        try {
            Page<User> list = userService.list(userDto);
            if (null != list) {
                return R.ok(list);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> checkUserValid(String name) {
        try {
            return userService.checkUserValid(name) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
