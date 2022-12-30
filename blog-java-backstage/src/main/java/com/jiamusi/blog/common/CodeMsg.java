package com.jiamusi.blog.common;


/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/28 10:26
 * @description：返回提示
 */
public class CodeMsg {
    private int retCode;
    private String message;
    // 按照模块定义CodeMsg
    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(101,"服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(102,"输入参数为空");
    // 业务异常
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(103,"查找不到对应数据");
    public static CodeMsg NOT_UPDATE = new CodeMsg(104,"操作失败，请重试！");
    public static CodeMsg USER_OR_PASS_ERROR = new CodeMsg(105,"账号或者密码错误，请重试！");
    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
