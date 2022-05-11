package cn.feedsheep.online_train_ticket.service;

import cn.feedsheep.online_train_ticket.model.entity.Ticket;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service
 * @ClassName : TicketService.java
 * @createTime : 2022/5/11 15:27
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface TicketService {
    List<Ticket> findByDateAndStation(Long dateTick, String startStation, String endStation);
}
