package cn.feedsheep.online_train_ticket;

import cn.feedsheep.online_train_ticket.service.TicketOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket
 * @ClassName : SpringBootTests.java
 * @createTime : 2022/5/13 20:00
 * @Email : 874280179@qq.com
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OnlineTrainTicketApplication.class})
public class SpringBootTests {

    @Autowired
    private TicketOrderService ticketOrderService;

    @Test
    public void test(){
        System.out.println(getRandomSeatNo());
    }

    /**
     * 分配随机座位
     * @return
     */
    private String getRandomSeatNo(){
        Random random = new Random();
        String seatNoStr = (random.nextInt(7) + 2) + "号车厢";

        int seatId = random.nextInt(13);
        if(seatId < 10){
            seatNoStr = seatNoStr + "0" + seatId;
        }else{
            seatNoStr = seatNoStr + seatId;
        }

        char seatChar = (char)('A' + random.nextInt(5));
        if(seatChar == 'E'){
            seatChar = 'F';
        }
        seatNoStr = seatNoStr + seatChar;

        return seatNoStr;
    }
}
