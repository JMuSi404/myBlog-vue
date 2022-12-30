package com.jiamusi.blog.web.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.entity.Tag;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *博客主页
 */
@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    /**
     * 主页博客列表
     */
    @GetMapping ("/indexBlogList")
    public Msg indexBlogList(@RequestParam (defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "6") int limit){
        Page<Blog> page = new Page<>(pageNum , limit);
        IPage<Blog> blogIPage = blogService.queryBlogsList(page);
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal( );
        HashMap<String, Object> map = new HashMap<>( 2);
        map.put("list",records);
        map.put("total",total);
        return Msg.success(map);
    }
    /**
     * 主页标签列表
     */
    @GetMapping("/indexTagList")
    public  Msg TagShowList(){
         List<Tag> tags = tagService.queryTagList( );
         return Msg.success(tags);
    }

    /**
          * 博客文章数量与评论数量
     */
    @GetMapping("/indexBlogsCount")
    public  Msg BlogsCount(){
        Map<String, Object> Map = blogService.selectCount( );
        return Msg.success(Map);
    }
}
