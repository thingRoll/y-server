package com.chhd.y.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties props;

    static {
        String fileName = "app.properties";
        props = new Properties();
        try {
            InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
            props.load(new InputStreamReader(in, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PropertiesUtils() {
    }

    public static String getProperty(String key) {
        return props.getProperty(key.trim()).trim();
    }
}
