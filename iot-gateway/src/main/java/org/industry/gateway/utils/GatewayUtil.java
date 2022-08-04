package org.industry.gateway.utils;


import cn.hutool.core.util.StrUtil;
import org.industry.common.exception.NotFoundException;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Objects;

public class GatewayUtil {

    /**
     * 获取远程IP
     * 优先级 由高到低
     * x-forwarded-for
     * Proxy-Client-IP
     * WL-Proxy-Client-IP
     * X-Real_IP
     * getRemoteAddress
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(ServerHttpRequest request) {
        // 用来识别使用负载均衡或者代理HTTP代理连接到WEB服务器的原始IP
        String ip = request.getHeaders().getFirst("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // 经过apache HTTP服务器才会有的字段，用apache HTTP做代理会添加这个请求头
            ip = request.getHeaders().getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // 经过apache HTTP服务器才会有的字段，用apache HTTP做代理会添加这个请求头
            ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "X-Real-IP".equalsIgnoreCase(ip)) {
            // nginx 代理一般会加上这个请求头
            ip = request.getHeaders().getFirst("X-Real_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        }
        return ip;
    }

    /**
     * 获取 Request Cookie
     *
     * @param request
     * @param key
     * @return
     */
    public static String getRequestCookie(ServerHttpRequest request, String key) {
        HttpCookie cookie = request.getCookies().getFirst(key);
        if (null == cookie || StrUtil.isBlank(cookie.getValue())) {
            throw new NotFoundException("Invalid request cookie of " + key);
        }
        return cookie.getValue();
    }
}
