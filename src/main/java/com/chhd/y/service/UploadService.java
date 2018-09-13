package com.chhd.y.service;

import com.chhd.y.common.Response;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    Response file(MultipartFile multipartFile, String path);

    Response files(MultipartFile[] multipartFiles, String path);
}
