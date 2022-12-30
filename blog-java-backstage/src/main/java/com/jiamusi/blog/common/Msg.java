package com.jiamusi.blog.common;


/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/28 10:26
 * @description：返回统一结果集
 */
public class Msg<T> {

    private String message;
    private int code;
    private T data;

    private Msg(T data) {
        this.code = 200;
        this.message = "成功";
        this.data = data;
    }

    private Msg(CodeMsg cm) {
        if(cm == null){
            return;
        }
        this.code = cm.getRetCode();
        this.message = cm.getMessage();
    }

    /**
     * 成功时候的调用
     * @return
     */
    public static <T> Msg<T> success(T data){
        return new Msg<T>(data);
    }

    /**
     * 成功，不需要传入参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Msg<T> success(){
        return (Msg<T>) success("");
    }
    /**
     * 失败时候的调用
     * @return
     */
    public static <T> Msg<T> error(CodeMsg cm){
        return new Msg<T>(cm);
    }
    /**
     * 失败时候的调用,扩展消息参数
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Msg<T> error(CodeMsg cm,String msg){
        cm.setMessage(cm.getMessage()+"--"+msg);
        return new Msg<T>(cm);
    }
    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
}
