package com.jiamusi.blog.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.annotation.UserLoginToken;
import com.jiamusi.blog.common.CodeMsg;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.entity.Tag;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagsController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;
    /**
     * 获取标签和数量
     * @return
     */
    @GetMapping("/getTags")
    public  Msg toTags(){
        List<Tag> tags = tagService.queryTagListAndCount();
        Integer  TagsCount= tagService.queryTagCount();
        HashMap<String, Object> map = new HashMap<>( 2);
        map.put("list",tags);
        map.put("total",TagsCount);
        return Msg.success(map);
    }


    /**
     * 添加标签
     * @param tagName
     * @return
     */
    @PostMapping("/admin/addTags")
    @UserLoginToken
    public  Msg saveTypes(String tagName){
            List<Tag> tags = tagService.queryByName(tagName);
            if (tags.size()>0){
                return  Msg.error(CodeMsg.NOT_UPDATE,"标签名已经被使用了");
            }
            Tag tag = new Tag();
            tag.setName(tagName);
            int i = tagService.saveTag(tag);
            if (i>0){
                return Msg.success();
            }else {
                return Msg.error(CodeMsg.NOT_UPDATE);
            }
    }

    /**
     * 查询标签
     * @param limit
     * @param pageNum
     * @return
     */
    @GetMapping("/admin/getTags")
    @UserLoginToken
    public  Msg QueryTypes(@RequestParam(defaultValue = "10")Integer limit,@RequestParam(defaultValue = "1") Integer pageNum) {
                Page<Tag> page = new Page<Tag>(pageNum , limit);
                IPage<Tag> tagPage = tagService.queryTagsPage(page);
              HashMap<String, Object> map = new HashMap<>(2);
              map.put("list",tagPage.getRecords());
              map.put("total",tagPage.getTotal());
              return  Msg.success(map);
    }


    /**
     * 删除标签
     * @param ids
     * @return
     */
    @PostMapping("/admin/removeTags/{ids}")
    @UserLoginToken
    public  Msg QueryTypes(@PathVariable List<String> ids){
        boolean isFlag = tagService.removeByIds(ids);
         if (isFlag){
              return  Msg.success();
            }else {
             return  Msg.error(CodeMsg.NOT_UPDATE);
            }
    }

    /**
     * 修改标签
     * @param
     * @return
     */
    @PostMapping("/admin/editTags")
    @UserLoginToken
    public  Object editTypes(@RequestBody  Tag tag){
        int i = tagService.editTagNameById(tag.getName( ),tag.getId( ));
        if (i>0){
            return  Msg.success();
        }else {
            return  Msg.error(CodeMsg.NOT_UPDATE);
        }
    }

}
