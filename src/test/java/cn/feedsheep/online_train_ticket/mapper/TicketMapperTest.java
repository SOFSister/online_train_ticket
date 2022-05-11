package cn.feedsheep.online_train_ticket.mapper;

import cn.feedsheep.online_train_ticket.OnlineTrainTicketApplicationTests;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.mapper
 * @ClassName : TicketMapperTest.java
 * @createTime : 2022/5/11 15:41
 * @Email : 874280179@qq.com
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TicketMapperTest extends OnlineTrainTicketApplicationTests {

    String[] cities = {"杭州市","温州市","上海市","广州市","嘉兴市"};
    int year = 2022;
    int month = 6;
    int day = 1;
    int hour = 9;

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    void test(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void save() {
        Random random = new Random();
        Ticket ticket = new Ticket();
        for (int k = 0; k < 30; k++) {//30天
            for (int i = 0; i < 5; i++) {//A
                for (int j = 0; j < 5; j++) {//B
                    if(i == j) continue;
                    for (int l = 0; l < 12; l++) {//12趟车
                        //得到出发时间
                        String start = "2022-06-";
                        int nowDay = day + k;
                        if (nowDay < 10){
                            start = start + "0";
                        }
                        start = start + nowDay + " ";

                        int nowHour = hour + l;
                        if (nowHour < 10){
                            start = start + "0";
                        }
                        start = start + nowHour + ":";

                        int nowMin = random.nextInt(31);
                        if (nowMin < 10){
                            start = start + "0";
                        }
                        start = start + nowMin + ":00";
                        //得到到达时间
                        String end = "2022-06-";
                        if (nowDay < 10){
                            end = end + "0";
                        }
                        end = end + nowDay + " ";
                        nowHour = nowHour + random.nextInt(2) + 1;
                        if (nowHour < 10){
                            end = end + "0";
                        }
                        end = end + nowHour + ":";

                        nowMin = random.nextInt(40) + 20;
                        end = end + nowMin + ":00";

                        ticket.setDepartureStation(cities[i]);
                        ticket.setNextStation(cities[j]);
                        ticket.setDepartureTime(getDate(start));
                        ticket.setArrivalTime(getDate(end));

                        String trainNumber = "G";
                        for (int m = 0; m < 4; m++) {
                            trainNumber = trainNumber + random.nextInt(10);
                        }
                        ticket.setTrainNumber(trainNumber);

                        ticket.setBusinessInventory(10);
                        ticket.setFirstInventory(20);
                        ticket.setSecondInventory(20);
                        ticket.setStandingInventory(20);
                        ticket.setBusinessPrice(150 + random.nextInt(30));
                        ticket.setFirstPrice(100 + random.nextInt(30));
                        ticket.setSecondPrice(70 + random.nextInt(10));
                        ticket.setStandingPrice(50 + random.nextInt(10));
                        ticket.setCreateTime(new Date());
                        ticketMapper.save(ticket);
                    }
                }
            }
        }
    }



    private Date getDate(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式(年-月-日-时-分-秒)
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}