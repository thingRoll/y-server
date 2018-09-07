package com.chhd.y.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    private MD5Utils() {
    }

    public static String encode(String text) {
        return DigestUtils.md5Hex(text + PropertiesUtils.getProperty("salt")).toLowerCase();
    }
}
