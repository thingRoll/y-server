package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Override
    public Response image(MultipartFile multipartFile, String path) {
        String originalFilename = multipartFile.getOriginalFilename();
        String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = UUID.randomUUID() + extensionName;

        File dir = new File(path);
        File targetFile = new File(dir, filename);
        try {
            // transferTo()，是SpringMVC封装的方法，用于图片上传时，从内存写入磁盘
            multipartFile.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
