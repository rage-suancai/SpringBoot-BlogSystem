package com.yxs.service.impl;

import com.yxs.domain.ResponseResult;
import com.yxs.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: oosUploadServiceImpl
 * @Desription:
 */
@Service("ossUploadService")
public class OssUploadServiceImpl implements UploadService {

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        return null;
    }

}
