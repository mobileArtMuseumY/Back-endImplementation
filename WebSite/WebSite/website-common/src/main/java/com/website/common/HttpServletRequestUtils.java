package com.website.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletRequestUtils.class);

    public static int getInt(HttpServletRequest request, String key){

        try {

            return Integer.decode(request.getParameter(key));
        }catch (Exception e) {
            LOGGER.error("HttpServletRequestUtils key:{} convert int is error:{}", key, e);
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){

        try {

            return Long.decode(request.getParameter(key));
        }catch (Exception e) {
            LOGGER.error("HttpServletRequestUtils key:{} convert long is error:{}", key, e);
            return -1L;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){

        try {

            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e) {
            LOGGER.error("HttpServletRequestUtils key:{} convert boolean is error:{}", key, e);
            return false;
        }
    }

    public static double getDouble(HttpServletRequest request, String key){

        try {

            return Double.valueOf(request.getParameter(key));
        }catch (Exception e) {
            LOGGER.error("HttpServletRequestUtils key:{} convert double is error:{}", key, e);
            return -1d;
        }
    }

    public static String getString(HttpServletRequest request, String key){

        try {
            String parameter = request.getParameter(key);

            if (StringUtils.isEmpty(parameter)) {
                return null;
            }

            return parameter.trim();

        }catch (Exception e) {
            LOGGER.error("HttpServletRequestUtils key:{} convert string is error:{}", key, e);
            return null;
        }
    }

}
