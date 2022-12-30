package com.jiamusi.blog.Listen;

import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;


@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ListenHandler {

    private final BlogService blogService;
    private final RedisUtil redisUtil;

    private static final String VIEW_KEY = "views";

    @Autowired
    public ListenHandler (BlogService blogService, RedisUtil redisUtil) {
        this.blogService = blogService;
        this.redisUtil = redisUtil;
    }

    @PostConstruct
    public void init() throws Exception {
        //将数据库中的数据写入redis
        List<Blog> blogList = blogService.list();
        blogList.forEach(blog -> {
            //将浏览量写入redis
            redisUtil.zAdd(VIEW_KEY, blog.getId().toString(), blog.getViews());
        });
    }


    @Scheduled (cron = "*/30 * * * * ?")
    public void updateNum() {
        Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores(VIEW_KEY, 0, 10);
        writeNum(viewNum, VIEW_KEY);
    }

    private void writeNum(Set<ZSetOperations.TypedTuple<String>> set, String fieldName) {
        set.forEach(item -> {
            Long id = Long.valueOf(item.getValue());
            Integer num = item.getScore().intValue();
            Blog blog = blogService.getById(id);
            //更新数据库
            if (blog!=null){
                blog.setViews(num);
                blogService.updateNumById(blog);
            }
        });
    }


    /**
     * 关闭时操作
      */
     @PreDestroy
     public void afterDestroy()  {
     //将redis中的数据写入数据库
     Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores(VIEW_KEY, 0, 10);
     writeNum(viewNum, VIEW_KEY);
     }

}
