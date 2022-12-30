package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiamusi.blog.entity.BlogTagRelation;
import com.jiamusi.blog.mapper.BlogTagRelationMapper;
import com.jiamusi.blog.service.BlogTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【t_blog_tag_relation】的数据库操作Service实现
 * @createDate 2022-05-23 21:56:21
 */
@Service
public class BlogTagRelationServiceImpl extends ServiceImpl<BlogTagRelationMapper, BlogTagRelation>
        implements BlogTagRelationService {
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public int saveBlogTagRelation (BlogTagRelation blogTagRelation) {
        return blogTagRelationMapper.insert(blogTagRelation);
    }


}




