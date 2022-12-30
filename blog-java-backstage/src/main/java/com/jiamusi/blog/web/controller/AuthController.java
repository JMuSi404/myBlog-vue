package com.jiamusi.blog.web.controller;

import cn.hutool.json.JSONObject;
import com.jiamusi.blog.annotation.UserLoginToken;
import com.jiamusi.blog.common.CodeMsg;
import com.jiamusi.blog.common.Msg;
import com.jiamusi.blog.entity.User;
import com.jiamusi.blog.entity.param.LoginParam;
import com.jiamusi.blog.service.UserService;
import com.jiamusi.blog.service.impl.TokenService;
import com.jiamusi.blog.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户信息
     * @return
     */
    @UserLoginToken
    @GetMapping("/admin/getInfo")
    public Msg<Object> list(){
        String tokenUserId = TokenUtil.getTokenUserId( );
        User user = userService.getById(tokenUserId);
        return Msg.success(user);
    }

    /**
     * 登录验证
     * @param response
     * @return
     */
    @PostMapping(value = "/admin/login")
    public Msg<Object> login(@RequestBody  LoginParam param, HttpServletResponse response) {
         if (param==null){
             return Msg.error(CodeMsg.PARAMETER_ISNULL);
         }
        User user = userService.getUserByUsernameAndPassword(param.getUsername(),param.getPassword());
        if (user!=null){
            JSONObject jsonObject = new JSONObject();
            String token = tokenService.getToken(user);
            jsonObject.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return Msg.success(jsonObject);
        }else {
            return Msg.error(CodeMsg.USER_OR_PASS_ERROR);
        }
    }

}
