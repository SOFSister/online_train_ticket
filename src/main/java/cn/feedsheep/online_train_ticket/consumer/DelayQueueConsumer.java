package cn.feedsheep.online_train_ticket.consumer;

import cn.feedsheep.online_train_ticket.config.DelayedQueueConfig;
import cn.feedsheep.online_train_ticket.mapper.TicketMapper;
import cn.feedsheep.online_train_ticket.mapper.TicketOrderMapper;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import cn.feedsheep.online_train_ticket.model.entity.TicketOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.consumer
 * @ClassName : DelayQueueConsumer.java
 * @createTime : 2022/5/14 17:34
 * @Email : 874280179@qq.com
 * @Description :
 * 消费者 基于插件的延迟消息
 */
@Slf4j
@Component
public class DelayQueueConsumer {

    @Autowired
    TicketOrderMapper ticketOrderMapper;

    @Autowired
    TicketMapper ticketMapper;

    //接收消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = new String(message.getBody());
        TicketOrder ticketOrder = null;
        try {
            ticketOrder = objectMapper.readValue(msg,TicketOrder.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("当前时间:{},收到延迟队列的消息:{}",new Date(),msg);

        //当订单状态为0（未支付时），将状态改为2（超时）
        Integer orderId = ticketOrder.getId();

        int i = ticketOrderMapper.updateToTimeOutIfStateZero(orderId);
        if (i > 0){
            log.info("订单id{}的订单已超时",msg);
            int seatNo = ticketOrder.getSeatLevel();
            int changeRow = 0;
            if(seatNo == 0){
                changeRow = ticketMapper.addBusinessTicket(ticketOrder.getTicketId());
            }else if(seatNo == 1){
                changeRow = ticketMapper.addFirstTicket(ticketOrder.getTicketId());
            }else if(seatNo == 2){
                changeRow = ticketMapper.addSecondTicket(ticketOrder.getTicketId());
            }else if(seatNo == 3){
                changeRow = ticketMapper.addStandingTicket(ticketOrder.getTicketId());
            }

            if(changeRow == 0){
                log.error("添加库存失败");
            }
        }

    }

}

