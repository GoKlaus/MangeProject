package org.industry.common.constant;

public interface CommonConstant {

    interface Response {
        String OK = "ok";
        String ERROR = "error";
    }

    interface Time {
        /**
         * 时区
         */
        String TIMEZONE = "GMT+8";

        /**
         * 时间格式化
         */
        String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String COMPLETE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    }
}
