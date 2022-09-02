package org.industry.common.sdk.utils;

import cn.hutool.core.convert.Convert;
import org.industry.common.bean.driver.AttributeInfo;
import org.industry.common.constant.ValueConstant;

import java.util.Map;

public class DriverUtil {

    public static <T> T attribute(Map<String, AttributeInfo> infoMap, String attribute) {
        return value(infoMap.get(attribute).getType(), infoMap.get(attribute).getValue());
    }


    public static <T> T value(String type, String value) {
        return Convert.convertByClassName(getTypeClassName(type), value);
    }

    public static String getTypeClassName(String type) {
        String className = String.class.getName();
        switch (type.toLowerCase()) {
            case ValueConstant.Type.SHORT:
                className = Short.class.getName();
                break;
            case ValueConstant.Type.INT:
                className = Integer.class.getName();
                break;
            case ValueConstant.Type.LONG:
                className = Long.class.getName();
                break;
            case ValueConstant.Type.FLOAT:
                className = Float.class.getName();
                break;
            case ValueConstant.Type.DOUBLE:
                className = Double.class.getName();
                break;
            case ValueConstant.Type.BOOLEAN:
                className = Boolean.class.getName();
                break;
            default:
                break;
        }
        return className;
    }
}
