package org.industry.fallback;

import org.industry.feign.UserClient;
import org.springframework.cloud.openfeign.FallbackFactory;

public class UserFallback implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
        };
    }
}
