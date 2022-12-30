package com.jiamusi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2022-05-23 21:56:41
* @Entity com.heshijia.myblog.pojo.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    Comment selectAllById (@Param ( "id" ) Long id);


    IPage<Comment> selectByBlogIdAndExtendsCommentidIsNull (Page page,@Param ( "blogId" ) Long blogId);


    List<Comment> selectByExtendsCommentid (@Param ( "extendsCommentid" ) Long extendsCommentid);

    int deleteByBlogId (@Param ( "blogId" ) Long blogId);

    IPage<Comment> selectByBlogId (Page page, @Param ( "blogId" ) Long blogId,@Param ( "name" ) String name);

    int deleteById (@Param ( "id" ) Long id);

    Integer  selectCommentCount();


}




