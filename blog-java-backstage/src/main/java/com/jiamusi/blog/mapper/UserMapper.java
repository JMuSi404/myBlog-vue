package com.jiamusi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiamusi.blog.entity.User;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-05-23 21:56:47
* @Entity com.heshijia.myblog.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword (@Param ( "username" ) String username , @Param ( "password" ) String password);

}




