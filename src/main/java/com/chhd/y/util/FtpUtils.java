package com.chhd.y.util;

import com.google.common.collect.Lists;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FtpUtils {

    private static String ftpIp = PropertiesUtils.getProperty("ftp.ip");
    private static String ftpPort = PropertiesUtils.getProperty("ftp.port");
    private static String ftpUser = PropertiesUtils.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtils.getProperty("ftp.pass");
    private static String ftpPath = PropertiesUtils.getProperty("ftp.path");

    private FtpUtils() {
    }

    public static boolean uploadFile(File file) {
        return uploadFile(Lists.newArrayList(file));
    }

    public static boolean uploadFile(List<File> fileList) {
        FileInputStream fis = null;
        FTPClient client = new FTPClient();
        try {
            client.connect(ftpIp, Integer.parseInt(ftpPort));
            client.login(ftpUser, ftpPass);
            client.setBufferSize(1024);
            client.setControlEncoding("UTF-8");
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            client.changeWorkingDirectory(ftpPath);
            for (File fileItem : fileList) {
                fis = new FileInputStream(fileItem);
                client.storeFile(fileItem.getName(), fis);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
