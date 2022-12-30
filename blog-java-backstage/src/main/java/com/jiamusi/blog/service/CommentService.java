package com.jiamusi.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.entity.Comment;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2022-05-23 21:56:41
*/
public interface CommentService extends IService<Comment> {

    int saveComment(Comment comment);

    IPage<Comment> queryByBlogIdAndExtendsCommentidIsNull (Page page,Long blogId);

    List<Comment> queryByExtendsCommentid (Long extendsCommentid);

    List<Blog> queryIdAndTitle ();

    IPage<Comment> queryByBlogId (Page page, Long blogId,String name);

    int removeCommentById (Long id);

    Comment queryAllById (Long id);

}
