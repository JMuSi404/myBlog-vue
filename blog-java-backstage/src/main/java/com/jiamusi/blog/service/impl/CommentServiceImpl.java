package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.entity.Comment;
import com.jiamusi.blog.mapper.BlogMapper;
import com.jiamusi.blog.mapper.CommentMapper;
import com.jiamusi.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【t_comment】的数据库操作Service实现
 * @createDate 2022-05-23 21:56:41
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int saveComment (Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public IPage<Comment> queryByBlogIdAndExtendsCommentidIsNull (Page page,Long blogId) {
        return commentMapper.selectByBlogIdAndExtendsCommentidIsNull(page,blogId);
    }

    @Override
    public List<Comment> queryByExtendsCommentid (Long extendsCommentid) {
        return commentMapper.selectByExtendsCommentid(extendsCommentid);
    }

    @Override
    public List<Blog> queryIdAndTitle ( ) {
        return blogMapper.selectIdAndTitle( );
    }

    @Override
    public IPage<Comment> queryByBlogId (Page page, Long blogId,String name) {
        return commentMapper.selectByBlogId(page,blogId,name);
    }

    @Override
    public int removeCommentById (Long id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public Comment queryAllById (Long id) {
        return commentMapper.selectAllById(id);
    }
}




