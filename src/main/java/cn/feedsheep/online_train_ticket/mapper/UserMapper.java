package cn.feedsheep.online_train_ticket.mapper;

import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.model.request.LoginRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.mapper
 * @ClassName : UserMapper.java
 * @createTime : 2022/5/8 15:09
 * @Email : 874280179@qq.com
 * @Description :
 */
@Repository
public interface UserMapper {

    int register(User user);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findByUserId(@Param("id") Integer userId);

}
