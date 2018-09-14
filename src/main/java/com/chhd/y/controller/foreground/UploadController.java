package com.chhd.y.controller.foreground;

import com.chhd.y.common.Response;
import com.chhd.y.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/upload/")
@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "file", method = RequestMethod.POST)
    @ResponseBody
    public Response file(@RequestParam MultipartFile file, HttpServletRequest request) {
        // ../Y-Server/target/y-server/upload 此目录缓存上传图片
        String path = request.getSession().getServletContext().getRealPath("upload");
        return uploadService.file(file, path);
    }

    @RequestMapping(value = "files", method = RequestMethod.POST)
    @ResponseBody
    public Response files(@RequestParam MultipartFile[] file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        return uploadService.files(file, path);
    }
}
