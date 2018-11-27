package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.common.ResponseEditor;
import com.chhd.y.controller.BaseController;
import com.chhd.y.service.UploadService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/upload/")
@Controller
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "file.do", method = RequestMethod.POST)
    @ResponseBody
    public Response file(@RequestParam MultipartFile file, HttpServletRequest request) {
        // ../Y-Server/target/y-server/upload 此目录缓存上传图片
        String path = request.getSession().getServletContext().getRealPath("upload");
        return uploadService.file(file, path);
    }

    @RequestMapping(value = "file_editor.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEditor fileEditor(@RequestParam MultipartFile file, HttpServletRequest request) {
        // ../Y-Server/target/y-server/upload 此目录缓存上传图片
        String path = request.getSession().getServletContext().getRealPath("upload");
        Response origin = uploadService.file(file, path);
        Map<String, String> fileMap = (Map<String, String>) origin.getData();
        ResponseEditor result = new ResponseEditor(origin.getStatus(), Lists.newArrayList(fileMap.get("url")));
        return result;
    }

    @RequestMapping(value = "files.do", method = RequestMethod.POST)
    @ResponseBody
    public Response files(@RequestParam MultipartFile[] file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        return uploadService.files(file, path);
    }
}
