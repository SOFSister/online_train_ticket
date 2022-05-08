package cn.feedsheep.online_train_ticket.model.request;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.model.request
 * @ClassName : RegisterRequest.java
 * @createTime : 2022/5/8 16:53
 * @Email : 874280179@qq.com
 * @Description :
 */
@Data
public class RegisterRequest {

    private String userPhone;

    private String userName;

    private String userIdCard;

    private String userMail;

    private String password;

}
