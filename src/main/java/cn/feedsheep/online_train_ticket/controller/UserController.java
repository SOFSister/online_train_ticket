package cn.feedsheep.online_train_ticket.controller;

import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import cn.feedsheep.online_train_ticket.model.request.RegisterRequest;
import cn.feedsheep.online_train_ticket.service.UserService;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.controller
 * @ClassName : UserController.java
 * @createTime : 2022/5/8 14:55
 * @Email : 874280179@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JsonData register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest) ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");
    }

    @PostMapping("/login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.login(loginRequest);
        return token != null ? JsonData.buildSuccess(token) : JsonData.buildError("登录失败，账号或密码错误");
    }

}
