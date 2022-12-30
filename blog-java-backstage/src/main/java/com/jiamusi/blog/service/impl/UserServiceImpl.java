package com.jiamusi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiamusi.blog.entity.User;
import com.jiamusi.blog.mapper.UserMapper;
import com.jiamusi.blog.service.UserService;
import com.jiamusi.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-05-23 21:56:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名
     * @param user
     * @return
     */
    public User findByUsername(User user){
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
    }

    public User findUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUserByUsernameAndPassword (String username , String password) {
        User user = userMapper.selectByUsernameAndPassword(username , MD5Utils.getMD5(password));
        return user;
    }
}




