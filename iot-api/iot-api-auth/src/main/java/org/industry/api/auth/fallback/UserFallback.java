package org.industry.api.auth.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.UserClient;
import org.industry.common.dto.UserDto;
import org.industry.common.model.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFallback implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new UserClient() {
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
        };
    }
}
