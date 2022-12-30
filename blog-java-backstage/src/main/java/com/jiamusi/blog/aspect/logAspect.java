package com.jiamusi.blog.aspect;


import com.jiamusi.blog.common.ReturningLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class logAspect {
/**
 * 获取请求信息和返回值输出到日志
   */
@Pointcut("execution(* com.jiamusi.blog.web.controller.*.*(..))")
public  void  logPointcut(){}



@Before("logPointcut()")
    public void BeforeLog(JoinPoint joinPoint){
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( );
    HttpServletRequest request = requestAttributes.getRequest( );
    String URL= request.getRequestURL( ).toString( );
    String ip = request.getRemoteAddr( );
    String classMethod = joinPoint.getSignature( ).getDeclaringTypeName( )+"."+joinPoint.getSignature( ).getName();
    Object[] args = joinPoint.getArgs( );
    ReturningLog returningLog = new ReturningLog(URL,ip,classMethod,args);
    log.info("请求信息:{}",returningLog);
   }

@AfterReturning(returning = "obj",pointcut = "logPointcut()")
public void AfterReturning(Object obj){
    log.info("返回值:{}",obj);
}









}
