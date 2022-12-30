package com.jiamusi.blog.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiamusi.blog.annotation.UserLoginToken;
import com.jiamusi.blog.common.*;
import com.jiamusi.blog.entity.Blog;
import com.jiamusi.blog.entity.Comment;
import com.jiamusi.blog.service.BlogService;
import com.jiamusi.blog.service.CommentService;
import com.jiamusi.blog.service.impl.BlogServiceImpl;
import com.jiamusi.blog.service.impl.CommentServiceImpl;
import com.jiamusi.blog.utils.DateTimeFormatUtils;
import com.jiamusi.blog.utils.MailDataUtils;
import com.jiamusi.blog.utils.QQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private JavaMailSender mailSender;

     /**
     * 查询博客评论(admin)
     * @param limit
     * @param pageNum
     * @return
     */
     @UserLoginToken
     @GetMapping("/admin/getComment")
    public  Msg QueryComment(@RequestParam (defaultValue = "10")Integer limit, @RequestParam(defaultValue = "1") Integer pageNum ,Long blogId,String name) {
          Page<Comment> page = new Page(pageNum,limit);
          IPage<Comment> commentPage = commentService.queryByBlogId(page ,blogId,name);
          HashMap<String, Object> map = new HashMap<>( 2 );
          map.put("list",commentPage.getRecords());
          map.put("total",commentPage.getTotal());
          return Msg.success(map );
    }




    /**
     * 删除评论(admin)
     * @param ids
     * @return
     */
    @UserLoginToken
    @PostMapping("/admin/removeComment/{ids}")
    public  Msg deleteComment(@PathVariable  List<String> ids) {
            boolean isFlag = commentService.removeByIds(ids);
            if (isFlag) {
                return Msg.success();
            } else {
                return Msg.error(CodeMsg.NOT_UPDATE);
            }
    }

    /**
     * 修改(admin)
     * @return
     */
    @UserLoginToken
    @PostMapping("/admin/editComment")
    public  Msg editComment(@RequestBody Comment comment) {
        boolean isFlag = commentService.updateById(comment);
        if (isFlag) {
            return Msg.success();
        } else {
            return Msg.error(CodeMsg.NOT_UPDATE);
        }
    }




    //查询评论
    @GetMapping ("/commentList")
    public  Msg  getComment(@RequestParam("blogId") Long blogId, @RequestParam (defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer limit) {
        if (blogId==null){
            return  Msg.error(CodeMsg.PARAMETER_ISNULL);
        }
        Page<Comment> page = new Page(pageNum,limit);
        IPage<Comment> commentIPage = commentService.queryByBlogIdAndExtendsCommentidIsNull(page , blogId);
        for (Comment record : commentIPage.getRecords( )) {
            List<Comment> comments = commentService.queryByExtendsCommentid(record.getId( ));
            record.setList(comments);
        }
        HashMap<String, Object> map = new HashMap<>(2);
         map.put("list" , commentIPage.getRecords( ));
         map.put("total",commentIPage.getTotal());
        return  Msg.success(map);
    }
    //新增评论
@PostMapping("/addComment")
public  Msg  addComment(@RequestBody  Comment comment){
      comment.setCreatetime(DateTimeFormatUtils.getDateTime(new Date(  )));
      int i = commentService.saveComment(comment);
      if (i>0){
        return  Msg.success();
       }else {
        return  Msg.error(CodeMsg.NOT_UPDATE);
       }
}
    //评论邮件提醒
    @PostMapping("/emailAlert")
   public  Msg  emailAlert(@RequestBody  Comment comment){
            SimpleMailMessage message = new SimpleMailMessage( );
           Blog blog =  blogService.getById(comment.getBlogId());
            if (comment.getExtendsCommentid( ) != null && comment.getExtendsCommentid( ) != 0) {
                Comment ExtendsComment = commentService.queryAllById(comment.getExtendsCommentid( ));
                // 发件人
                message.setFrom(MailDataUtils.MAIL_SENDER);
                // 收件人
                message.setTo(ExtendsComment.getEmail( ));
                // 邮件标题
                message.setSubject(MailDataUtils.MAIL_TITLE);
                // 邮件内容
                message.setText("评论人:" + comment.getNickname( ) +
                        "\n评论内容:" + comment.getContent( ) +
                        "\n文章:" + blog.getTitle( ));
                mailSender.send(message);
            } else {
                // 发件人
                message.setFrom(MailDataUtils.MAIL_SENDER);
                // 抄送人
                message.setTo(MailDataUtils.MAIL_CC);
                // 邮件标题
                message.setSubject(MailDataUtils.MAIL_TITLE);
                // 邮件内容
                message.setText("评论人:" + comment.getNickname( ) +
                        "\n评论内容:" + comment.getContent( ) +
                        "\n文章:" + blog.getTitle( ));
                mailSender.send(message);
            }
            return Msg.success();
    }

    /**
     * 拉取qq头像和昵称
     * @param qq
     * @return
     */
    @GetMapping("/getQQInfo/{qq}")
    public Msg qq(@PathVariable String qq) {
        if (StringUtils.isEmpty(qq)) {
            return Msg.error(CodeMsg.PARAMETER_ISNULL);
        }
            QQ qqInfo = QQUtils.getQQInfo(Long.parseLong(qq));
            HashMap<String, Object> map = new HashMap<>( 3);
            map.put("avatar",qqInfo.getAvatar());
            map.put("nickname",qqInfo.getName());
            map.put("email", qq + "@qq.com");
            return  Msg.success(map);
    }
}
