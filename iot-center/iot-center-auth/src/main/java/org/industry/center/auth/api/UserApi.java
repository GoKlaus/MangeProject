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
        return null;
    }

    @Override
    public R<Boolean> delete(String id) {
        return null;
    }

    @Override
    public R<User> update(User user) {
        return null;
    }

    @Override
    public R<Boolean> restPassword(String id) {
        return null;
    }

    @Override
    public R<User> selectById(String id) {
        return null;
    }

    @Override
    public R<User> selectByName(String name) {
        return null;
    }

    @Override
    public R<Page<User>> list(UserDto userDto) {
        return null;
    }

    @Override
    public R<Boolean> checkUserValid(String name) {
        return null;
    }
}
