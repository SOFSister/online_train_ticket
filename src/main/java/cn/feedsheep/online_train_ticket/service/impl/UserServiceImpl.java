package cn.feedsheep.online_train_ticket.service.impl;

import cn.feedsheep.online_train_ticket.mapper.UserMapper;
import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import cn.feedsheep.online_train_ticket.model.request.RegisterRequest;
import cn.feedsheep.online_train_ticket.service.UserService;
import cn.feedsheep.online_train_ticket.utils.CommonUtils;
import cn.feedsheep.online_train_ticket.utils.JWTUtils;
import cn.feedsheep.online_train_ticket.utils.MatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service.impl
 * @ClassName : UserServiceImpl.java
 * @createTime : 2022/5/8 15:08
 * @Email : 874280179@qq.com
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(RegisterRequest registerRequest) {

        if(checkRegisterRequest(registerRequest)){
            User user = new User();

            user.setCreateTime(new Date());
            user.setUserName(registerRequest.getUserName());
            user.setEmail(registerRequest.getUserMail());
            user.setPhone(registerRequest.getUserPhone());
            user.setIdCard(registerRequest.getUserIdCard());
            user.setPwd(CommonUtils.MD5(registerRequest.getPassword()));

            int i = userMapper.register(user);
            return i == 1 ? true : false;

        }else{
            return false;
        }

    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userMapper.findByPhoneAndPwd(loginRequest.getUserPhone(),CommonUtils.MD5(loginRequest.getPassword()));

        if (user == null){
            return null;
        }

        String token = JWTUtils.geneJsonWebToken(user);

        return token;
    }

    /**
     * 判断请求数据是否正确
     * @param registerRequest
     * @return
     */
    private boolean checkRegisterRequest(RegisterRequest registerRequest){
        //判断数据是否为空
        if(registerRequest.getPassword() == null || registerRequest.getUserMail() == null ||
        registerRequest.getUserIdCard() == null || registerRequest.getUserName() == null ||
        registerRequest.getUserPhone() == null){
            return false;
        }

        //判断手机号、身份证号、邮箱正则表达式
        return MatcherUtils.isEmail(registerRequest.getUserMail()) &&
                MatcherUtils.isIDNumber(registerRequest.getUserIdCard()) &&
                MatcherUtils.isPhoneNumber(registerRequest.getUserPhone());

    }

}
