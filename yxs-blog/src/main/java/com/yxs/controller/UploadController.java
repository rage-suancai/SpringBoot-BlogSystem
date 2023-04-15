package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: UploadController
 * @Desription:
 */
@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img) {

        return uploadService.uploadImg(img);

    }

}
