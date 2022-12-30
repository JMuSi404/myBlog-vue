package com.jiamusi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiamusi.blog.entity.User;

/**
* @author Administrator
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-05-23 21:56:47
*/
public interface UserService extends IService<User> {


  User findByUsername(User user);

  User findUserById(String userId);

  User  getUserByUsernameAndPassword(String username,String password);
}
