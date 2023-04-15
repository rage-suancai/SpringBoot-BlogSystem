package com.yxs.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yxs.domain.ResponseResult;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.service.UploadService;
import com.yxs.utils.PathUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: oosUploadServiceImpl
 * @Desription:
 */
@Service("ossUploadService")
@ConfigurationProperties(prefix = "oss")
public class OssUploadServiceImpl implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        String originalFilename = img.getOriginalFilename(); // 获取原始文件名
        if (!originalFilename.endsWith(".png")) throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);

        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img, filePath); // 如果判断通过上传文件到OSS
        return ResponseResult.okResult(url);

    }

    private String uploadOss(MultipartFile imgFile, String filePath) {

        Configuration cfg = new Configuration(Region.autoRegion()); // 构造一个带指定 Region 对象的配置类
        UploadManager uploadManager = new UploadManager(cfg); // ...其他参数参考类注释
        String key = filePath; // 默认不指定key的情况下 以文件内容的hash值作为文件名

        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class); // 解析上传成功的结果
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rt53dh6j3.hd-bkt.clouddn.com/" + key;
            } catch (QiniuException ex) {
                Response r = ex.response; System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";

    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

}
