package cn.feedsheep.online_train_ticket.service;

import cn.feedsheep.online_train_ticket.model.entity.TicketOrder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service
 * @ClassName : TicketOrderService.java
 * @createTime : 2022/5/12 21:18
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface TicketOrderService {
    Map<String,Object> saveTicketOrder(Integer userId, Integer ticketId, Integer seatNo) throws Exception;
}
