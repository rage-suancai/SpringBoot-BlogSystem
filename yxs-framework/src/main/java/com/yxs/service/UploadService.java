package com.yxs.service;

import com.yxs.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    ResponseResult uploadImg(MultipartFile img);

}
