package com.jiamusi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiamusi.blog.entity.BlogTagRelation;

/**
* @author Administrator
* @description 针对表【t_blog_tag_relation】的数据库操作Service
* @createDate 2022-05-23 21:56:21
*/
public interface BlogTagRelationService extends IService<BlogTagRelation> {

    int   saveBlogTagRelation(BlogTagRelation blogTagRelation);


}
