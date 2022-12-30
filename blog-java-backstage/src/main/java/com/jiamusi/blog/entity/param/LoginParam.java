package com.jiamusi.blog.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {
    private  String id;

    private  String username;

    private  String password;
}
