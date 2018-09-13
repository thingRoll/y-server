package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.service.UploadService;
import com.chhd.y.util.FtpUtils;
import com.chhd.y.util.PropertiesUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Override
    public Response file(MultipartFile multipartFile, String path) {
        MultipartFile[] multipartFiles = new MultipartFile[]{multipartFile};
        Response<List> response = files(multipartFiles, path);
        Object object = response.getData().get(0);
        return Response.createBySuccess(object);
    }

    @Override
    public Response files(MultipartFile[] multipartFiles, String path) {
        List<Object> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String originalFilename = multipartFile.getOriginalFilename();
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String filename = UUID.randomUUID() + "." + extensionName;

            File cacheDir = new File(path);
            File cacheFile = new File(cacheDir, filename);
            if (!cacheDir.exists()) {
                boolean b1 = cacheDir.setWritable(true);
                boolean b2 = cacheDir.mkdirs();
            }
            boolean isSuccess;
            try {
                // transferTo()，是SpringMVC封装的方法，用于图片上传时，从内存写入磁盘
                multipartFile.transferTo(cacheFile);
                isSuccess = FtpUtils.uploadFile(Lists.newArrayList(cacheFile));
                boolean b = cacheFile.delete();

                String ftpHttpPrefix = PropertiesUtils.getProperty("ftp.http.prefix");
                Map<String, String> fileMap = Maps.newHashMap();
                fileMap.put("filename", originalFilename);
                fileMap.put("url", ftpHttpPrefix + cacheFile.getName());
                fileList.add(fileMap);
            } catch (Exception e) {
                isSuccess = false;
                e.printStackTrace();
            }
            if (!isSuccess) {
                return Response.createByError();
            }
        }
        return Response.createBySuccess(fileList);
    }
}
