package com.chhd.y.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private static Logger logger = LoggerFactory.getLogger(LogUtils.class.getName());

    private LogUtils() {
    }

    public static void i(String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------------- Log Start -------------");
        for (String arg : args) {
            sb.append("\n").append(arg);
        }
        sb.append("\n------------- Log End -------------");
        logger.info(sb.toString());
    }
}
