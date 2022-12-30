package com.jiamusi.blog.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.annotation.UserLoginToken;
import com.jiamusi.blog.entity.Image;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotoWallController {

    @Autowired
    private ImageService imageService;
    /**
     * 照片墙列表
     * @return
     */
    @GetMapping ("/getImageList")
    public  Msg   getImage(@RequestParam (defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "12") int limit){
        Page<Image> page = new Page<>(pageNum, limit);
        Page<Image> imagePage = imageService.page(page);
        List<Image> records = imagePage.getRecords();
        long total = imagePage.getTotal( );
        HashMap<String, Object> map = new HashMap<>( 2);
        map.put("list",records);
        map.put("total",total);
        return Msg.success(map);
    }


    /**
     *照片墙图片上传
     * @param file
     * @return
     */
    @PostMapping ("/admin/imageUpload")
    public  Msg   imageUpload(@RequestParam("file") MultipartFile file){
        return imageService.qiNiuImageUpload(file);
    }

    /**
     *照片墙图片删除
     * @param ids
     * @return
     */
    @UserLoginToken
    @PostMapping("/admin/deleteImage/{ids}")
    public  Msg   deleteImage(@PathVariable List<String> ids){
        if (ids==null ||ids.size()==0){
            return Msg.error(null,"照片墙id不能为空");
        }else {
            boolean flag = imageService.removeByIds(ids);
            if (flag){
                return Msg.success();
            }else{
                return Msg.error(null);
            }
        }
    }
}
