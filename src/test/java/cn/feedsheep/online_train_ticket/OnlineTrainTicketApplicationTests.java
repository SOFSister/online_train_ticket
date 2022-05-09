package cn.feedsheep.online_train_ticket;

import cn.feedsheep.online_train_ticket.model.entity.User;
import cn.feedsheep.online_train_ticket.utils.CacheService;
import cn.feedsheep.online_train_ticket.utils.EmailUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineTrainTicketApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    void contextLoads() {

    }

    /**
     * 功能描述：添加字符串到redis
     */
    @Test
    void add() {

    }


}
