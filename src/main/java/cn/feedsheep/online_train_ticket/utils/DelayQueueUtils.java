package cn.feedsheep.online_train_ticket.utils;

import cn.feedsheep.online_train_ticket.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.utils
 * @ClassName : DelayQueueUtils.java
 * @createTime : 2022/5/14 17:40
 * @Email : 874280179@qq.com
 * @Description :
 */
@Component
@Slf4j
public class DelayQueueUtils {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到延迟队列
     * @param message 发送的消息
     * @param delayTime 延迟时间 毫秒
     */
    public void sendDelayMsg(String message,Integer delayTime){
        log.info("当前时间{},发送一条时长{}毫秒信息给延迟队列delayed.queue：{}",new Date().toString(),delayTime,message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,DelayedQueueConfig.DELAYED_ROUTING_KEY,message, msg ->{
            //发送消息的时候 延迟时长
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
    }

}
