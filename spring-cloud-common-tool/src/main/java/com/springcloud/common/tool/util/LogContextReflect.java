package com.springcloud.common.tool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 通过反射获取requestId
 *
 * @author jiangyf
 */
public class LogContextReflect {
    private static Logger logger = LoggerFactory.getLogger(LogContextReflect.class);

    private static Method requestQueryMethod;
    private static volatile boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            synchronized (LogContextReflect.class) {
                if (!hasInit) {
                    try {
                        Class logContextClass = Class.forName("com.springcloud.*.web.BaseController", false, LogContextReflect.class.getClassLoader());
                        requestQueryMethod = logContextClass.getMethod("getRequestId");
                        requestQueryMethod.invoke(null);
                    } catch (ClassNotFoundException e) {
                        logger.warn("log context not found", e.getMessage());
                        requestQueryMethod = null;
                    } catch (NoSuchMethodException e) {
                        logger.warn("query requestId method not found", e.getMessage());
                        requestQueryMethod = null;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        logger.warn("query requestId error", e.getMessage());
                        requestQueryMethod = null;
                    }
                    hasInit = true;
                }
            }
        }
    }

    public static void setMDC() {
        if (!hasInit) {
            init();
        }

        if (requestQueryMethod != null) {
            try {
                MDC.put("requestId", (String) requestQueryMethod.invoke(null));
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.warn("query requestId error", e.getMessage());
            }
        }
    }

    public static String getReqeustId() {
        if (!hasInit) {
            init();
        }

        if (requestQueryMethod != null) {
            try {
                return (String) requestQueryMethod.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.warn("query requestId error", e.getMessage());
            }
        }

        return UUID.randomUUID().toString().replace("-", "");
    }
}
