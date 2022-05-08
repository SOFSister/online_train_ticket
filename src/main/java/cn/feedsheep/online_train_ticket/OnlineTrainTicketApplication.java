package cn.feedsheep.online_train_ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.feedsheep.online_train_ticket.mapper")
public class OnlineTrainTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTrainTicketApplication.class, args);
    }

}
