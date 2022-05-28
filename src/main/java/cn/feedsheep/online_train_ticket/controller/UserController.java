package cn.feedsheep.online_train_ticket.controller;

import cn.feedsheep.online_train_ticket.cache.Cache;
import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import cn.feedsheep.online_train_ticket.model.request.RegisterRequest;
import cn.feedsheep.online_train_ticket.service.UserService;
import cn.feedsheep.online_train_ticket.utils.CacheService;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private CacheService redisUtils;


    @PostMapping("/register")
    public JsonData register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest) ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");
    }

    @PostMapping("/login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.login(loginRequest);
        return token != null ? JsonData.buildSuccess(token) : JsonData.buildError(1,"登录失败，账号或密码错误");
    }

    @PostMapping("/send_email_code")
    public JsonData sendEmailCode(@RequestBody Map<String, String> email){

        return userService.sendEmailCode(email) ? JsonData.buildSuccess() : JsonData.buildError(4,"发送邮件失败，请重试");

    }

    @PostMapping("/verify_email_code")
    public JsonData verifyEmailCode(@RequestBody Map<String, String> emailAndCode){

        return userService.verifyEmailCode(emailAndCode) ? JsonData.buildSuccess() : JsonData.buildError(6,"验证码错误，请重试");

    }

    @GetMapping("/user_info_by_token")
    public JsonData userInfoByToken(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        if(userId == null){
            return JsonData.buildError(-2,"获取用户信息错误，请重试");
        }else{
            User user = userService.userInfoByUserId(userId);

            return JsonData.buildSuccess(user);
        }
    }
}
