package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.jiamusi.blog.common.CodeMsg;
import com.jiamusi.blog.common.MsgCode;
import com.jiamusi.blog.entity.Image;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.mapper.ImageMapper;
import com.jiamusi.blog.service.ImageService;
import com.jiamusi.blog.utils.QiNiuUtiles;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

/**
* @author Administrator
* @description 针对表【t_image】的数据库操作Service实现
* @createDate 2022-12-05 14:10:18
*/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image>
    implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Msg qiNiuImageUpload (MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换,拼接uuid
        fileName = UUID.randomUUID()+suffixName;

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
          //...生成上传凭证，然后准备上传
        String accessKey = QiNiuUtiles.ACCESSKEY;
        String secretKey = QiNiuUtiles.SECRETKEY;
        String bucket = QiNiuUtiles.BUCKET;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            InputStream inputStream = file.getInputStream( );
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,fileName,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                Image image = new Image( );
                image.setUrl(QiNiuUtiles.DOMAINNAME+putRet.key);
                image.setDesc(file.getOriginalFilename());
                imageMapper.insert(image);
                HashMap<String, Object> map = new  HashMap<String, Object>();
                map.put("key",putRet.key);
                map.put("hash",putRet.hash);
                return Msg.success(map);
            } catch (QiniuException ex) {
                 throw new QiniuException(ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Msg.error(null,ex.getMessage());
        }
    }
}




