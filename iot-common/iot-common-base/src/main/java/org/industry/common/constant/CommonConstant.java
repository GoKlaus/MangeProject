package org.industry.common.constant;

public interface CommonConstant {

    interface Algorithm {
        /**
         * 默认密钥
         */
        String DEFAULT_KEY = "pnoker/dc3";

        /**
         * 默认密码
         */
        String DEFAULT_PASSWORD = "dc3dc3dc3";

        /**
         * 加密算法 对称AES
         */
        String ALGORITHM_AES = "AES";

        /**
         * 加密算法 非对称RSA
         */
        String ALGORITHM_RSA = "RSA";

        /**
         * 加密算法 SHA256withRSA
         */
        String ALGORITHM_SHA256_RSA = "SHA256withRSA";

        /**
         * 证书类型 X.509
         */
        String CERTIFICATE_X509 = "X.509";

        /**
         * 证书类型 PKCS12
         */
        String CERTIFICATE_PKCS12 = "PKCS12";

        /**
         * 证书类型 JKS
         */
        String CERTIFICATE_JKS = "jks";
    }

    interface Symbol {
        /**
         * 分隔符
         */
        String SEPARATOR = "::";
    }

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
