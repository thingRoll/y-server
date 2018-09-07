package com.chhd.y.util;

public class FtpUtils {

    private static String ftpIp = PropertiesUtils.getProperty("ftp.ip");
    private static String ftpUser = PropertiesUtils.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtils.getProperty("ftp.pass");

    private FtpUtils() {
    }


}
