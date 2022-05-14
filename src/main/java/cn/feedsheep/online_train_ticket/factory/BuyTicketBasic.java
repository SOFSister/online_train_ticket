package cn.feedsheep.online_train_ticket.factory;

import cn.feedsheep.online_train_ticket.model.entity.Ticket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory.ticket_inventory_impl
 * @ClassName : BuyTicketBasic.java
 * @createTime : 2022/5/13 15:49
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface BuyTicketBasic {

    Ticket buyTicket(Ticket ticket);

}
