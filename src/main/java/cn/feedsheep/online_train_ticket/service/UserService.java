package cn.feedsheep.online_train_ticket.service;

import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import cn.feedsheep.online_train_ticket.model.request.RegisterRequest;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service.impl
 * @ClassName : UserService.java
 * @createTime : 2022/5/8 15:02
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    boolean register(RegisterRequest registerRequest);

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    String login(LoginRequest loginRequest);

    /**
     * 发送邮箱验证码
     * @param email
     */
    boolean sendEmailCode(Map<String, String> userEmail);

    /**
     * 验证邮箱验证码
     * @param emailAndCode
     * @return
     */
    boolean verifyEmailCode(Map<String, String> emailAndCode);
}
