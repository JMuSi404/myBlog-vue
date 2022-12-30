package com.jiamusi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiamusi.blog.entity.Image;
import com.jiamusi.blog.common.Msg;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Administrator
* @description 针对表【t_image】的数据库操作Service
* @createDate 2022-12-05 14:10:18
*/
public interface ImageService extends IService<Image> {


  Msg qiNiuImageUpload(MultipartFile file);
}
