package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.entity.BlogTagRelation;
import com.jiamusi.blog.entity.Tag;
import com.jiamusi.blog.entity.param.BlogQueryParam;
import com.jiamusi.blog.mapper.BlogMapper;
import com.jiamusi.blog.mapper.BlogTagRelationMapper;
import com.jiamusi.blog.mapper.CommentMapper;
import com.jiamusi.blog.mapper.TagMapper;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【t_blog】的数据库操作Service实现
 * @createDate 2022-05-23 21:56:09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
        implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Blog> queryBlogList (BlogQueryParam param) {
        List<Blog> blogs = blogMapper.selectBlogQuery(param);
        List<BlogTagRelation> blogTagRelations = blogTagRelationMapper.selectList(null);
        List<Tag> tags = tagMapper.selectList(null);
        for (BlogTagRelation  blogTagRelationsItem:blogTagRelations) {
            for (Blog  blogsItem:blogs) {
                if (blogsItem.getId( ).longValue() == blogTagRelationsItem.getBlogId( ).longValue()) {
                    List<String> tagsName = blogsItem.getTagsName( );
                    for (Tag tagsItem:tags) {
                            if (tagsItem.getId( ).longValue() == blogTagRelationsItem.getTagId( ).longValue()) {
                                tagsName.add(tagsItem.getName( ));
                            }
                    }
                    blogsItem.setTagsName(tagsName);
                }
            }
        }
        return blogs;
    }

    @Override
    public int saveBlog (Blog blog) {
        return blogMapper.insert(blog);
    }

    @Override
    public List<Blog> queryBlogCondition (Map<String, Object> map) {
        return blogMapper.selectBlogConditionPage(map);
    }

    @Override
    public IPage<Blog> queryBlogsList (Page page) {
        return blogMapper.selectBlogsShowList(page);
    }

    @Override
    public IPage<Blog> queryBlogsSearchResults (Page page,String condition) {
        return blogMapper.selectBlogsSearchResults(page,condition);
    }

    @Override
    public int removeBlogAndBlogTagRelation (Long id) {
        blogTagRelationMapper.deleteByBlogId(id);
        commentMapper.deleteByBlogId(id);
        int j = blogMapper.deleteById(id);
        return j;
    }

    @Override
    public Blog queryBlogById (Long id) {
        return blogMapper.selectAllById(id);
    }

    @Override
    public IPage<Blog> queryBlogByTagId (Page page,Long TagId) {
        return blogMapper.selectBlogByTagId(page,TagId);
    }

    @Override
    public int editBlogs (Blog blog , String tagIds) {
        blogTagRelationMapper.deleteByBlogId(blog.getId( ));
        int j = blogMapper.updateById(blog);
        if (j > 0) {
            if (tagIds != null && tagIds != "") {
                String[] ids = tagIds.split(",");
                for (String id : ids) {
                    int k = blogTagRelationMapper.insert(new BlogTagRelation(null , blog.getId( ) , Long.parseLong(id)));
                    if (k <= 0) {
                        return k;
                    }
                }
            }
        }
        return j;
    }

    @Override
    public Blog queryBlogDetailsById (Long id) {
        Blog blog = blogMapper.selectBlogDetailsById(id);
        String content = blog.getContent( );
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public Blog queryBlogDetailsByType (String type) {
        Blog blog = blogMapper.selectBlogIdByType(type);
        return blog;
    }

    @Override
    public Map<String, List<Blog>> queryBlogArchive ( ) {
        Map<String, List<Blog>> Map = new HashMap<>( );
        List<String> Years = blogMapper.selectBlogYear( );
        for (String year : Years) {
            List<Blog> blogs = blogMapper.selectBlogArchive(year);
            Map.put(year , blogs);
        }
        return Map;
    }

    @Override
    public Map<String, Object> selectCount ( ) {
        HashMap<String, Object> HashMap = new HashMap<>( );
        Integer BlogCount = blogMapper.selectBlogCount( );
        Integer CommentCount = commentMapper.selectCommentCount( );
        Integer TagCount = tagMapper.selectTagCount( );
        Integer ViewCount = blogMapper.selectBlogViewsCount( );
        HashMap.put("ViewCount" , ViewCount);
        HashMap.put("TagCount" , TagCount);
        HashMap.put("BlogCount" , BlogCount);
        HashMap.put("CommentCount" , CommentCount);
        return HashMap;
    }

    @Override
    public Boolean updateNumById (Blog blog) {
        return blogMapper.updateViewsById(blog.getViews( ) , blog.getId( )) > 1;
    }

    @Override
    public List<Blog> queryBlogByType (String type) {
        return blogMapper.selectByType(type);
    }


}




