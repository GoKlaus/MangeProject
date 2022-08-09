package org.industry.common.constant;

public interface CacheConstant {

    interface Timeout {
        /**
         * salt 在 redis 中的失效时间，分钟
         */
        int SALT_CACHE_TIMEOUT = 5;
        /**
         * user 登陆限制失效时间，分钟
         */
        int USER_LIMIT_TIMEOUT = 5;
        /**
         * token 在 redis 中的失效时间，小时
         */
        int TOKEN_CACHE_TIMEOUT = 12;

    }

    interface Entity {
        String USER = "user";
    }

    interface Suffix {
        String ID = "_id";
        String TENANT_ID = "_tenant_id";
        String USER_ID = "_user_id";
        String DRIVER_ID = "_driver_id";
        String GROUP_ID = "_group_id";
        String PROFILE_ID = "_profile_id";
        String POINT_ID = "_point_id";
        String DEVICE_ID = "_device_id";
        String ATTRIBUTE_ID = "_attribute_id";
        String ENTITY_ID = "_entity_id";

        String NAME = "_name";
        String PHONE = "_phone";
        String EMAIL = "_email";
        String SALT = "_salt";
        String TOKEN = "_token";
        String LIMIT = "_limit";
        String VALUE = "_value";
        String STATUS = "_status";
        String TYPE = "_type";
        String UNIT = "_unit";
        String IP = "_ip";
        String HOST_PORT = "_host_port";
        String SERVICE_NAME = "_service_name";

        String LIST = "_list";
        String DIC = "_dic";
    }
}
