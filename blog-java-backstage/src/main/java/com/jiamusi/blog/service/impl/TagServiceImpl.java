package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiamusi.blog.entity.Tag;
import com.jiamusi.blog.mapper.BlogTagRelationMapper;
import com.jiamusi.blog.mapper.TagMapper;
import com.jiamusi.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【t_tag】的数据库操作Service实现
 * @createDate 2022-05-23 21:56:44
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public int saveTag (Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public List<Tag> queryByName (String name) {
        return tagMapper.selectAllByName(name);
    }

    @Override
    public IPage<Tag> queryTagsPage (Page<Tag> page) {
        return tagMapper.selectPage(page , null);
    }

    @Override
    public int removeTagsById (Long id) {
        blogTagRelationMapper.deleteByTagId(id);
        int j = tagMapper.delById(id);
        return j;
    }

    @Override
    public Tag queryAllById (Long id) {
        return tagMapper.selectAllById(id);
    }


    @Override
    public int editTagNameById (String name , Long id) {
        return tagMapper.updateNameById(name , id);
    }

    @Override
    public List<Tag> queryTagList ( ) {
        return tagMapper.selectList(null);
    }

    @Override
    public List<Tag> queryTagListAndCount ( ) {
        return tagMapper.selectAllAndcount( );
    }

    @Override
    public List<Tag> queryTagListByBlogId (Long id) {
        return tagMapper.selectTagListByBlogId(id);
    }

    @Override
    public Integer queryTagCount ( ) {
        return tagMapper.selectTagCount( );
    }


}




