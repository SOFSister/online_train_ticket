package cn.feedsheep.online_train_ticket.factory;

import cn.feedsheep.online_train_ticket.model.entity.Ticket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory
 * @ClassName : BuyTicketBasic.java
 * @createTime : 2022/5/13 15:11
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface TicketInventoryBasic {

    Integer getTicketInventory(Ticket ticket);

}
