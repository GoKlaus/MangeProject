package org.industry.center.auth.service;

import org.industry.center.auth.bean.TokenValid;

public interface TokenService {
    TokenValid checkTokenValid(String name, String salt, String token);
}
