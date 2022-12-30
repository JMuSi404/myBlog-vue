package com.jiamusi.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@MapperScan ("com.jiamusi.blog.mapper")
@SpringBootApplication
public class BlogApplication {

    public static void main (String[] args) {
        SpringApplication.run(BlogApplication.class , args);
    }

}
