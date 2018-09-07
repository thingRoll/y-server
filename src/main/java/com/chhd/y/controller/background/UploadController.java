package com.chhd.y.controller.background;

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

    @RequestMapping(value = "image", method = RequestMethod.POST)
    @ResponseBody
    public Response image(@RequestParam MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        uploadService.image(file, path);
        return Response.createBySuccess();
    }
}
