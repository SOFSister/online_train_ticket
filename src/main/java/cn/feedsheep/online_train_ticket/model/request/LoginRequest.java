package cn.feedsheep.online_train_ticket.model.request;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.model.request
 * @ClassName : LoginRequset.java
 * @createTime : 2022/5/8 18:31
 * @Email : 874280179@qq.com
 * @Description :
 */
@Data
public class LoginRequest {

    /**
     * 用户手机
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String password;
}
