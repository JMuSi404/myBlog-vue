package com.jiamusi.blog.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.annotation.UserLoginToken;
import com.jiamusi.blog.common.CodeMsg;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.entity.BlogTagRelation;
import com.jiamusi.blog.entity.Tag;
import com.jiamusi.blog.entity.param.BlogQueryParam;
import com.jiamusi.blog.entity.param.PublishedParam;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.service.BlogTagRelationService;
import com.jiamusi.blog.service.TagService;
import com.jiamusi.blog.utils.DateTimeFormatUtils;
import com.jiamusi.blog.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *文章控制器
 */
@RestController
@RequestMapping("/api")
public class blogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagRelationService blogTagRelationService;


    /**
     * 文章详情
     *
     * @return
     */
    @GetMapping ( "/getBlogDetails" )
    public Msg getBlogDetails (@RequestParam ( "blogId" ) String id) {
        Blog blog = blogService.queryBlogDetailsById(Long.parseLong(id));
        return Msg.success(blog);
    }

    /**
     * 文章详情(关于我,友链)
     * @return
     */
    @GetMapping ( "/getDetailsByType" )
    public  Msg getDetailsByType(String type){
        Blog blogByType = blogService.queryBlogDetailsByType(type);
        if (blogByType!=null){
            Blog blog = blogService.queryBlogDetailsById(blogByType.getId());
            return Msg.success(blog);
        }else {
            return Msg.error(CodeMsg.NOT_FIND_DATA);
        }
    }


    /**
     * 文章归档信息
     *
     * @return
     */
    @GetMapping ( "/getBlogArchive" )
    public Msg getBlogArchive (@RequestParam ( defaultValue = "1" ) int pageNum ,
                               @RequestParam ( defaultValue = "12" ) int limit) {
        Page<Blog> page = new Page<>(pageNum , limit);
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>( );
        queryWrapper.eq(Blog::getPublished , 1).eq(Blog::getType , 0).orderByDesc(Blog::getCreatetime).select(Blog::getId , Blog::getCreatetime , Blog::getTitle , Blog::getFlag);
        IPage<Blog> BlogInfo = blogService.page(page , queryWrapper);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("list" , BlogInfo.getRecords( ));
        map.put("total" , BlogInfo.getTotal( ));
        return Msg.success(map);
    }


    /**
     * 根据标签获取文章列表
     *
     * @return
     */
    @GetMapping ( "/getBlogByTag/{TagId}" )
    public Msg blogTagsShowList (@RequestParam ( defaultValue = "1" ) Integer pageNum ,
                                 @RequestParam ( defaultValue = "6" ) Integer limit ,
                                 @PathVariable ( "TagId" ) String TagId) {
        Page<Blog> page = new Page<>(pageNum , limit);
        IPage<Blog> blogIPage = blogService.queryBlogByTagId(page , Long.parseLong(TagId));
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("list" , blogIPage.getRecords( ));
        map.put("total" , blogIPage.getTotal( ));
        return Msg.success(map);
    }


    /**
     * 搜索文章
     *
     * @param
     * @param
     * @return
     */
    @GetMapping ( "/getBlogByKeyed" )
    public Msg BlogSearchResults (
            @RequestParam ( defaultValue = "" ) String title ,
            @RequestParam ( defaultValue = "1" ) Integer pageNum ,
            @RequestParam ( defaultValue = "3" ) Integer limit) {
        Page<Blog> page = new Page<>(pageNum , limit);
        IPage<Blog> blogIPage = blogService.queryBlogsSearchResults(page , title);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("list" , blogIPage.getRecords( ));
        map.put("total" , blogIPage.getTotal( ));
        return Msg.success(map);
    }


    /**
     * 文章列表
     *
     * @param
     * @param
     * @return
     */
    @UserLoginToken
    @PostMapping ( "/admin/getBlogList" )
    public Msg getBlogList (@RequestBody BlogQueryParam param) {
        List<Blog> blogs = blogService.queryBlogList(param);
        return Msg.success(blogs);
    }

    /**
     * 所有文章列表(id,name)
     *
     * @param
     * @param
     * @return
     */
    @UserLoginToken
    @GetMapping ( "/admin/getBlogListIdAndName" )
    public Msg getBlogListIdAndName() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>( );
        queryWrapper.select(Blog::getId,Blog::getTitle);
        List<Blog> blogs = blogService.list(queryWrapper);
        return Msg.success(blogs);
    }




    /**
     * 批量删除文章
     *
     * @param
     * @param
     * @return
     */
    @UserLoginToken
    @PostMapping ( "/admin/deleteBlogs" )
    public Msg deleteBlogs (@RequestParam ( "ids" ) String ids) {
        if (ids == null || ids == "") {
            return Msg.error(CodeMsg.PARAMETER_ISNULL);
        }
        String[] split = ids.split(",");
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>( );
        queryWrapper.in(Blog::getId , split);
        boolean isFlag = blogService.remove(queryWrapper);
        if (isFlag) {
            return Msg.success( );
        } else {
            return Msg.error(CodeMsg.NOT_UPDATE);
        }
    }

    /**
     * 获取文章详情(编辑)
     *
     * @return
     */
    @UserLoginToken
    @GetMapping ( "/admin/getBlogDetails" )
    public Msg getBlogDetailsAdmin (@RequestParam ( "blogId" ) String id) {
        Blog blog = blogService.getById(id);
        List<Tag> tags = tagService.queryTagListByBlogId(Long.parseLong(id));
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("blog" , blog);
        map.put("tags" , tags);
        return Msg.success(map);
    }

    /**
     * 修改发布状态
     *
     * @return
     */
    @UserLoginToken
    @PostMapping ( "/admin/updatePublished" )
    public Msg updatePublished (@RequestBody PublishedParam param) {
        LambdaUpdateWrapper<Blog> updateWrapper = new LambdaUpdateWrapper<>( );
        updateWrapper.eq(Blog::getId , param.getId( ));
        updateWrapper.set(Blog::getPublished , param.isPublished( ));
        boolean isFlag = blogService.update(updateWrapper);
        if (isFlag) {
            return Msg.success( );
        } else {
            return Msg.error(CodeMsg.NOT_UPDATE);
        }
    }

    /**
     * 添加博客信息
     */
    @UserLoginToken
    @PostMapping ( "/admin/addBlog" )
    public Msg saveBlogs (@RequestBody  Blog blog) {
        if (blog==null){
            return Msg.error(CodeMsg.PARAMETER_ISNULL);
        }
        if(!blog.getType().equals("0")){
            List<Blog> blogs = blogService.queryBlogByType(blog.getType( ));
            if (blogs.size()>0){
                return Msg.error(CodeMsg.NOT_UPDATE,"友情链接页或关于我页已存在");
            }
        }
        String tagIds=  blog.getTagIds();
        blog.setUserId(Long.parseLong(TokenUtil.getTokenUserId( )));
        blog.setViews(0);
        blog.setCreatetime(DateTimeFormatUtils.getDateTime(new Date(  )));
       int i=  blogService.saveBlog(blog);
        if (i>0) {
            if (tagIds != null && tagIds != "") {
                String[] ids = tagIds.split(",");
                for (String id : ids) {
                    int j = blogTagRelationService.saveBlogTagRelation(new BlogTagRelation(null , blog.getId( ) , Long.parseLong(id)));
                    if (j <= 0) { return Msg.error(CodeMsg.NOT_UPDATE); }
                }
            }
            return Msg.success( );
        }
       else {
            return Msg.error(CodeMsg.NOT_UPDATE);
      }
    }


       /**
        * 修改博客信息
         */
    @UserLoginToken
    @PostMapping ( "/admin/editBlog" )
    public Msg editBlogs(@RequestBody Blog blog){
        try{
            blog.setEdittime(DateTimeFormatUtils.getDateTime(new Date()));
            String tagIds=blog.getTagIds();
            int i = blogService.editBlogs(blog , tagIds);
            if (i>0){
                return Msg.success();
            }else {
                return Msg.error(CodeMsg.PARAMETER_ISNULL);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Msg.error(CodeMsg.SERVER_EXCEPTION);
        }
    }
}
