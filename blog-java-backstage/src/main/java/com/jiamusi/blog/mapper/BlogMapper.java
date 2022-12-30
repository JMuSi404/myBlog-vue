package com.jiamusi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.entity.param.BlogQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【t_blog】的数据库操作Mapper
* @createDate 2022-05-23 21:56:09
* @Entity com.heshijia.myblog.pojo.Blog
*/
public interface BlogMapper extends BaseMapper<Blog> {


  List<Blog> selectBlogQuery(@Param("param") BlogQueryParam param);


  List<Blog> selectBlogConditionPage(Map<String,Object> map);

  Blog selectAllById (@Param ( "id" ) Long id);

  IPage<Blog> selectBlogsShowList(Page page);

  IPage<Blog>  selectBlogsSearchResults(Page page,String condition);

  Blog selectBlogDetailsById(@Param ( "id" ) Long id);


  IPage<Blog> selectBlogByTagId(Page page,Long id);

  List<String> selectBlogYear();

  List<Blog> selectBlogArchive(String year);

  int updateViewsById (@Param ( "views" ) Integer views , @Param ( "id" ) Long id);

  List<Blog> selectIdAndTitle ();

  Integer  selectBlogCount();

   Integer  selectBlogViewsCount();

  List<Blog> selectByType (@Param ( "type" ) String type);

  Blog selectBlogIdByType(@Param ( "type" ) String type);

}




