package com.jiamusi.blog.aspect;



import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.utils.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 通过aop用redis更新浏览量
 */
@Aspect
@Component
public class RedisAspect {
    private final BlogService blogService;
    private final RedisUtil redisUtil;

    public RedisAspect(BlogService blogService, RedisUtil redisUtil) {
        this.blogService = blogService;
        this.redisUtil = redisUtil;
    }

    @Pointcut("execution(public * com.jiamusi.blog.service.impl.BlogServiceImpl.queryBlogDetailsById(..))")
    public void myPointCut(){}


    @After ("myPointCut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        Object[] objs=joinPoint.getArgs();
        Long id=(Long) objs[0];
        //根据id更新浏览量
        redisUtil.zIncrementScore("views",id.toString(),1);
    }
}
