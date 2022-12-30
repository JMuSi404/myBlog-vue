package com.jiamusi.blog.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiNiuUtiles implements InitializingBean {
    @Value ("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private  String secretKey;
    @Value("${qiniu.bucket}")
    private  String bucket;
    @Value("${qiniu.domainName}")
    private  String domainName;

    public static String DOMAINNAME;
    public static String ACCESSKEY;
    public static String SECRETKEY;
    public static String BUCKET;
    @Override
    public void afterPropertiesSet ( ) throws Exception {
        ACCESSKEY=accessKey;
        SECRETKEY=secretKey;
        BUCKET=bucket;
        DOMAINNAME=domainName;
    }
}
