package cn.feedsheep.online_train_ticket.service.impl;

import cn.feedsheep.online_train_ticket.exception.DataException;
import cn.feedsheep.online_train_ticket.exception.UserException;
import cn.feedsheep.online_train_ticket.mapper.UserMapper;
import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import cn.feedsheep.online_train_ticket.model.request.RegisterRequest;
import cn.feedsheep.online_train_ticket.service.UserService;
import cn.feedsheep.online_train_ticket.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private CacheService cacheService;

    @Override
    public boolean register(RegisterRequest registerRequest) {

        if(checkRegisterRequest(registerRequest)){

            int rows = userMapper.selectByPhoneOrEmail(registerRequest.getUserPhone(),registerRequest.getUserMail());
            if(rows > 0){
                throw new UserException(11,"邮箱或手机号已被创建");
            }
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

    @Override
    public boolean sendEmailCode(Map<String, String> userEmail) {
        String email = userEmail.getOrDefault("userMail",null);
        if(email == null){
            throw new DataException(-2,"存在数据为null");
        }else if(MatcherUtils.isEmail(email) == false){

            throw new UserException(2,"邮箱格式错误");

        }else{
            String emailCode = "";
            Random random = new Random();

            for (int i = 0; i < 6; i++) {
                emailCode = emailCode + random.nextInt(10);
            }

            //存入redis
            String emailCodeKey = getEmailCodeRedisKeyByEmail(email);
            cacheService.add(emailCodeKey,emailCode,5,TimeUnit.MINUTES);

            System.out.println(cacheService.get(emailCodeKey));

            //发送邮件
            emailUtils.sendSimpleTextMailActual("火车票网上购票系统","注册验证码：" + emailCode + "\n验证码五分钟内有效，请勿将验证码泄露给他人", new String[]{email},null,null,null);

            return true;
        }
    }

    @Override
    public boolean verifyEmailCode(Map<String, String> emailAndCode) {

        String email = emailAndCode.getOrDefault("userMail",null);
        String code = emailAndCode.getOrDefault("mailCode",null);

        if(email == null || code == null){
            throw new DataException(-2,"存在数据为null");
        }else if(MatcherUtils.isEmail(email) == false){
            throw new UserException(2,"邮箱格式错误");
        }else{
            String emailCodeKey = getEmailCodeRedisKeyByEmail(email);

            String aimCode = cacheService.get(emailCodeKey);

            if (aimCode == null){
                throw new UserException(5,"验证码过期");
            }

            if(aimCode.equals(code)){
                return true;
            }else{
                return false;
            }

        }
    }

    @Override
    public User userInfoByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
        return user;
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
            throw new DataException(-2,"存在数据为null");
        }

        //判断手机号、身份证号、邮箱正则表达式
        if(MatcherUtils.isEmail(registerRequest.getUserMail()) == false){
            throw new UserException(2,"邮箱格式错误");
        }
        if(MatcherUtils.isIDNumber(registerRequest.getUserIdCard()) == false){
            throw new UserException(3,"身份证格式错误");
        }
        if(MatcherUtils.isPhoneNumber(registerRequest.getUserPhone()) == false){
            throw new UserException(4,"电话号码格式错误");
        }
        return true;

    }

    private String getEmailCodeRedisKeyByEmail(String email){
        return "VerifyCode" + email + "code";
    }

}
