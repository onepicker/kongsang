package com.ks.oss.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ks.oss.service.OssService;
import com.ks.oss.utils.ContandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ContandPropertiesUtils.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ContandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ContandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ContandPropertiesUtils.BUCKET_NAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。

        String filename = file.getOriginalFilename();
        //避免文件重复上传解决方案一
      /*  String s = UUID.randomUUID().toString().replace("-", "");
        filename = s+filename;*/

        //方案二
        String s = new DateTime().toString("yyyy/MM/dd");
        filename = s+filename;

// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, filename, inputStream);
            String url = "https://"+bucketName+"."+endpoint+"/"+filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ossClient.shutdown();
        }

// 关闭OSSClient。

    }
}
