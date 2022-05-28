package cn.feedsheep.online_train_ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.feedsheep.online_train_ticket.mapper")
@EnableAsync
@EnableRetry
@EnableScheduling
public class OnlineTrainTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTrainTicketApplication.class, args);
    }

}
